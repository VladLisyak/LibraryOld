package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.BookValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;
import ua.nure.lisyak.SummaryTask4.util.Constants.*;

/**
 * Servlet for adding books.
 */
@WebServlet(Actions.Admin.Books.ADD)
@MultipartConfig(
        location = Settings.Books.TEMP_DIR,
        fileSizeThreshold = Settings.FILE_SIZE_THRESHOLD
)
public class Add extends BookServlet {

	private static final long serialVersionUID = 3213860117720057760L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setSelect(request);
        getResult(request);
        forward(getForwardPage(), request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book bookToAdd = getBookFromRequest(request, response, true);
        if (bookToAdd == null) {
            return;
        }
        Integer publisherId = getIntParam(request, "publisherId");
        List<Integer> authorsIds = getIntParamValues(request, "authors");
        Validator validator = new BookValidator(bookToAdd, publisherId, authorsIds, getInterp(), getLocale(request), getLocales());
        Part imagePart = request.getPart(Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !Settings.isProperImageType(imagePart.getContentType())) {
            validator.putIssue(Attributes.IMAGE, "validator.invalidFileFormat");
        }

        if (validator.hasErrors()) {
            forwardBack(request, response, validator, bookToAdd);
            return;
        }
        bookToAdd.setPublisher(getPublisherService().getById(publisherId));
        bookToAdd.setAuthors(getAuthorService().getAll(authorsIds));
        saveBook(imagePart, bookToAdd);
        setResult(request, true, "res.bookAdded");
        redirectToAction(Actions.Admin.Books.BOOKS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Books.ADD;
    }

    private void saveBook(Part imagePart, Book bookToSave){
        Book savedBook = saveBook(bookToSave);
        
        if (imagePart != null && imagePart.getSize() != 0) {
            String imageName = getFileService().saveFile(savedBook.getId(), Settings.UPLOAD_BOOKS_DIRECTORY, imagePart);
            savedBook.setImage(imageName);
            getBookService().updateWithImg(savedBook);
        }
    }

    private Book saveBook(Book bookToSave) {
        Interpretation title = getInterpretationService().add(bookToSave.getTitle());
        Interpretation description = getInterpretationService().add(bookToSave.getDescription());
        bookToSave.setTitle(title);
        bookToSave.setDescription(description);
        return getBookService().add(bookToSave);
    }

}
