package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.AuthorValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;


/**
 * Servlet for adding a new {@link Author}.
 */
@WebServlet(Actions.Admin.Authors.ADD)
@MultipartConfig(
        location = Constants.Settings.Authors.TEMP_DIR,
        fileSizeThreshold = Constants.Settings.FILE_SIZE_THRESHOLD
)
public class Add extends AuthorServlet {

	private static final long serialVersionUID = 3511702454258799027L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author authorToAdd = getAuthorFromRequest(request, response, true);
        if (authorToAdd == null) {
            return;
        }
        Validator authorValidator = new AuthorValidator(authorToAdd, getInterp(), getLocale(request), getLocales());
        Part imagePart = request.getPart(Constants.Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !Constants.Settings.isProperImageType(imagePart.getContentType())) {
            authorValidator.putIssue(Constants.Attributes.IMAGE, "validate.invalidFileFormat");
        }

        if (authorValidator.hasErrors()) {
            forwardBack(request, response, authorValidator, authorToAdd);
            return;
        }

        saveAuthor(imagePart, authorToAdd);
        setResult(request, true, "res.authorAdded");
        redirectToAction(Actions.Admin.Authors.AUTHORS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Authors.ADD;
    }

    private void saveAuthor(Part imagePart, Author authorToSave) {
        Author savedAuthor = saveAuthor(authorToSave);
        if (imagePart != null && imagePart.getSize() != 0) {
            String image = getFileService().saveFile(savedAuthor.getId(), Constants.Settings.UPLOAD_AUTHORS_DIR, imagePart);
            savedAuthor.setImage(image);
            getAuthorService().update(savedAuthor);
        }
    }

    private Author saveAuthor(Author authorToSave) {
        Interpretation name = getInterpretationService().add(authorToSave.getName());
        Interpretation description = getInterpretationService().add(authorToSave.getDescription());
        authorToSave.setName(name);
        authorToSave.setDescription(description);
        return getAuthorService().add(authorToSave);
    }


}
