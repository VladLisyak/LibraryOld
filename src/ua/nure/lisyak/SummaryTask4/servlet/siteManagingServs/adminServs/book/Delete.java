package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.util.Constants.*;
/**
 * Servlet for book deleting books.
 */
@WebServlet(Actions.Admin.Books.DELETE)
public class Delete extends BookServlet {

	private static final long serialVersionUID = -8791467827411794373L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book bookToDelete = getBook(request, response);
        if (bookToDelete == null) {
            return;
        }
        request.setAttribute(Attributes.BOOK, bookToDelete);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book bookToDelete = getBook(request, response);
        if (bookToDelete == null) {
            return;
        }
        int booksCount = getOrderService().countByBook(bookToDelete);
        if (booksCount != 0) {
            setResult(request, false, "res.cannotDeleteBook");
            redirectToAction(Actions.Admin.Books.DELETE, request, response);
            return;
        }

        getFileService().removeFile(bookToDelete.getImage(), Settings.UPLOAD_BOOKS_DIRECTORY);
        getBookService().delete(bookToDelete);
        setResult(request, true, "res.bookDeleted");
        redirectToAction(Actions.Admin.Books.BOOKS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Books.DELETE;
    }

}
