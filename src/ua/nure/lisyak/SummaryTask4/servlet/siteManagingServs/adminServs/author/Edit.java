package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.AuthorValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Constants.Settings;


/**
 * Servlet for {@link Author} editing.
 */
@WebServlet(Actions.Admin.Authors.EDIT)
@MultipartConfig(
        location = Constants.Settings.Authors.TEMP_DIR,
        fileSizeThreshold = Constants.Settings.FILE_SIZE_THRESHOLD
)
public class Edit extends AuthorServlet {

	private static final long serialVersionUID = 2092639353632719772L;
	private static final Logger LOGGER = Logger.getLogger(Edit.class);

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author updAuthor = getAuthor(request, response);
        if (updAuthor == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.AUTHOR, updAuthor);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author updAuthor = getAuthorFromRequest(request, response, false);
        
        if (updAuthor == null) {
            return;
        }

        Author oldAuthor = getAuthorService().getById(updAuthor.getId());
        if (oldAuthor == null) {
            redirectBack(request, response);
            return;
        }

        Validator authorValidator = new AuthorValidator(updAuthor, getInterp(), getLocale(request), getLocales());
        Part imagePart = request.getPart(Constants.Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && ! Settings.isProperImageType(imagePart.getContentType())) {
            authorValidator.putIssue(Constants.Attributes.IMAGE, "validate.invalidFileFormat");
            LOGGER.debug("validator.invalidFileFormat");
        }

        if (authorValidator.hasErrors()) {
            updAuthor.setImage(oldAuthor.getImage());
            forwardBack(request, response, authorValidator, updAuthor);
            return;
        }

        updateAuthor(request, oldAuthor, updAuthor, imagePart);
        setResult(request, true, "res.authorEdited");
        redirectToAction(Actions.Admin.Authors.AUTHORS_PATH, request, response);

    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Authors.EDIT;
    }

    private void updateAuthor(HttpServletRequest request, Author oldAuthor, Author updAuthor, Part imagePart) {
        Boolean deleteImage = Boolean.parseBoolean(request.getParameter("delete-" + Constants.Attributes.IMAGE));
        updateAuthor(oldAuthor, updAuthor);
        if (imagePart == null || imagePart.getSize() == 0) {
            if (deleteImage) {
                setImage(oldAuthor, null);
                return;
            }
            return;
        }

        String image = getFileService().saveFile(oldAuthor.getId(), Settings.UPLOAD_AUTHORS_DIR, imagePart);
        setImage(oldAuthor, image);
    }

    private void updateAuthor(Author oldAuthor, Author updAuthor) {
        Interpretation name = oldAuthor.getName();
        Interpretation description = oldAuthor.getDescription();
        name.setEn(updAuthor.getName().getEn());
        name.setRu(updAuthor.getName().getRu());
        description.setEn(updAuthor.getDescription().getEn());
        description.setRu(updAuthor.getDescription().getRu());
        getInterpretationService().update(name);
        getInterpretationService().update(description);
    }

    private void setImage(Author author, String image) {
        author.setImage(image);
        getAuthorService().update(author);
    }
}
