package ua.nure.lisyak.SummaryTask4.servlet.mainServs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.lisyak.SummaryTask4.servlet.BaseServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.ProcessResult;


/**
 * Basic servlet for all Main module servlets.
 */
public abstract class BaseMain extends BaseServlet {
	private static final long serialVersionUID = 4111699825155298257L;

    @Override
    protected void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getCurrentUser(request) != null) {
            List<Integer> orderedBooks = getOrderedBooks(request);
            request.setAttribute("orderedBooks", orderedBooks);
            request.setAttribute("orderedBooksCount", orderedBooks.size());
        }
        super.forward(page, request, response);
    }

    protected void getResult(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProcessResult result = (ProcessResult) session.getAttribute(Constants.Attributes.PROCESS_RESULT_SESSION);
        request.setAttribute(Constants.Attributes.PROCESS_RESULT, result);
        session.removeAttribute(Constants.Attributes.PROCESS_RESULT_SESSION);
    }

    protected void setResult(HttpServletRequest request, boolean succeeded, String message) {
        HttpSession session = request.getSession();
        ProcessResult procResult = new ProcessResult();
        procResult.setSucceeded(succeeded);
        procResult.setMessage(getInterp().translate(message, getLocale(request)));
        session.setAttribute(Constants.Attributes.PROCESS_RESULT_SESSION, procResult);
    }

    @SuppressWarnings("unchecked")
    protected List<Integer> getOrderedBooks(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Integer> orderedBooks = (List<Integer>) session.getAttribute(Constants.Attributes.ORDERED_BOOKS);
        return orderedBooks == null ? new ArrayList<Integer>() : orderedBooks;
    }

    @SuppressWarnings("unchecked")
    protected List<Integer> updateOrderList(HttpServletRequest request, int bookId, int quantity) {
        HttpSession session = request.getSession();
        List<Integer> orderedBooks = (List<Integer>) session.getAttribute(Constants.Attributes.ORDERED_BOOKS);

        if (orderedBooks == null) {
            orderedBooks = new ArrayList<Integer>();
        }
        if (quantity <= 0) {
            orderedBooks.remove(new Integer(bookId));
        } else {
            orderedBooks.add(bookId);
        }
        session.setAttribute(Constants.Attributes.ORDERED_BOOKS, orderedBooks);
        return orderedBooks;
    }

    protected void unsetOrderList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.Attributes.ORDERED_BOOKS);
    }

}
