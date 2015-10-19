package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.bookUsers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.BookUser;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.LibraryServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

import java.io.IOException;


@WebServlet(Actions.Lib.USER_BOOKS)
public class List extends LibraryServlet {

	private static final long serialVersionUID = 4892650578407737052L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.util.List<BookUser> userBooks = getUserService().getBooks();
        request.setAttribute("userBooks", userBooks);
        forward(PagesPaths.Lib.USER_BOOKS, request, response);
    }
}
