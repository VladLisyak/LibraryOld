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
 * Responsible for book editing.
 */
@WebServlet(Actions.Admin.Books.EDIT)
@MultipartConfig(
        location = Settings.Books.TEMP_DIR,
        fileSizeThreshold = Settings.FILE_SIZE_THRESHOLD
)
public class Edit extends BookServlet {

	private static final long serialVersionUID = -8684031443804333698L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book bookToEdit = getBook(request, response);
        if (bookToEdit == null){
            return;
        }
        request.setAttribute(Attributes.BOOK, bookToEdit);
        setSelect(request);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	Book bookToEdit = getBookFromRequest(request, response, false);
        if (bookToEdit == null) {
            return;
        }
        Book oldBook = getBookService().getById(bookToEdit.getId());
        if (oldBook == null) {
            redirectBack(request, response);
            return;
        }
        Integer pubId = getIntParam(request, "publisherId");
        List<Integer> authorsIds = getIntParamValues(request, "authors");
        Validator bookValidator = new BookValidator(
                bookToEdit, pubId, authorsIds, getInterp(), getLocale(request), getLocales()
        );
        Part imagePart = request.getPart(Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !Settings.isProperImageType(imagePart.getContentType())) {
            bookValidator.putIssue(Attributes.IMAGE, "validator.invalidFileFormat");
        }

        bookToEdit.setPublisher(getPublisherService().getById(pubId));
        bookToEdit.setAuthors(getAuthorService().getAll(authorsIds));
        
        if (Integer.valueOf(bookToEdit.getAmount()) != null && bookToEdit.getAmount() < oldBook.getAmount() - oldBook.getInStock()) {
            bookValidator.putIssue("amount", "validator.bookAmount");
        }
        if (bookValidator.hasErrors()) {
            bookToEdit.setImage(oldBook.getImage());
            forwardBack(request, response, bookValidator, bookToEdit);
            return;
        }
        bookToEdit.setInStock(oldBook.getInStock() + (bookToEdit.getAmount() - oldBook.getAmount()));
        updateBook(request, oldBook, bookToEdit, imagePart);
        setResult(request, true, "res.bookEdited");
        redirectToAction(Actions.Admin.Books.BOOKS_PATH, request, response);

    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Books.EDIT;
    }

    private void updateBook(HttpServletRequest request, Book oldBook, Book updBook, Part imagePart) {
        Boolean deleteImage = Boolean.parseBoolean(request.getParameter("delete-" + Attributes.IMAGE));
        if (imagePart == null || imagePart.getSize() == 0) {
            if (deleteImage) {
                setImage(updBook, null);
                updateBook(oldBook, updBook, true);
                return;
            }
            updateBook(oldBook, updBook, false);
            return;
        }

        String image = getFileService().saveFile(oldBook.getId(), Settings.UPLOAD_BOOKS_DIRECTORY, imagePart);
        setImage(updBook, image);
        updateBook(oldBook, updBook, true);

    }

    private void updateBook(Book oldBook, Book updBook, boolean updateImage) {
        Interpretation title = oldBook.getTitle();
        Interpretation description = oldBook.getDescription();
        title.clone(updBook.getTitle());
        description.clone(updBook.getDescription());
        getInterpretationService().update(title);
        getInterpretationService().update(description);
        if (updateImage){
            getBookService().updateWithImg(updBook);
        } else {
            getBookService().update(updBook);
        }
    }

    private void setImage(Book book, String image) {
        book.setImage(image);
    }
}
