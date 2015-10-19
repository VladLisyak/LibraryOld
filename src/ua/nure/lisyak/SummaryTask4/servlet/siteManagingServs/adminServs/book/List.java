package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet for viewing a list of {@link Book}s.
 */
@WebServlet(Actions.Admin.Books.BOOKS_PATH)
public class List extends DashboardServlet {

	private static final long serialVersionUID = -5880606388177125101L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.util.List<Book> books = getBookService().getAll();
        request.setAttribute(Constants.Attributes.BOOKS, books);
        getResult(request);
        forward(PagesPaths.Dashboard.Books.LIST, request, response);
    }
}
