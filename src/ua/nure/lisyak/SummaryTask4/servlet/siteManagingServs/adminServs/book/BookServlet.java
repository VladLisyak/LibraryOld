package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.Constants.*;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Basic servlet for books managing servlets.
 */
public abstract class BookServlet extends DashboardServlet {

	private static final long serialVersionUID = 1726795724888532070L;
	private static final Logger LOGGER = Logger.getLogger(BookServlet.class);

	protected Book getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = getIntParam(request, Attributes.ID);
        if (bookId == null){
            redirectBack(request, response);
            return null;
        }
        Book book = getBookService().getById(bookId);
        if (book == null){
            redirectBack(request, response);
            return null;
        }
        return book;
    }

    protected void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.bookNotFound");
        redirectToAction(Actions.Admin.Books.BOOKS_PATH, request, response);
    }

    protected void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, Book book)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute(Attributes.BOOK, book);
        setSelect(request);
        forward(getForwardPage(), request, response);
    }

    protected Book getBookFromRequest(HttpServletRequest request, HttpServletResponse response, boolean isNew) throws ServletException, IOException {
        Book book = new Book();
        if (!isNew){
            Integer bookId = getIntParam(request, Attributes.ID);
            if (bookId == null) {
                redirectBack(request, response);
                return null;
            }
            book.setId(bookId);
        }
        Interpretation title = getInterpretation(request, Attributes.TITLE);
        Interpretation description = getInterpretation(request, Attributes.DESCRIPTION);
        book.setTitle(title);
        book.setDescription(description);
        LOGGER.warn(request.getParameter("amount"));
        book.setAmount(getIntParam(request, "amount"));
        book.setPages(getIntParam(request, "pages"));
        book.setYear(getIntParam(request, "year"));
        return book;
    }

    protected void setSelect(HttpServletRequest request) {
        request.setAttribute(Attributes.AUTHORS, getAuthorService().getAll());
        request.setAttribute(Attributes.PUBLISHERS, getPublisherService().getAll());
    }

    protected abstract String getForwardPage();


}
