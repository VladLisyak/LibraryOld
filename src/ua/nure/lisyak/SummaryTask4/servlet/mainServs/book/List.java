package ua.nure.lisyak.SummaryTask4.servlet.mainServs.book;

import java.io.IOException;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for viewing {@link Book}s list.
 */
@WebServlet(Actions.Common.Books.LIST)
public class List extends BaseMain {
	private static final long serialVersionUID = 7100200499767975675L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderBy = getStringParam(request, "orderBy", "default");
        String search = getStringParam(request, "search");
        int page = getIntParam(request, "page", 1);
        
        Pair<java.util.List<Book>, Integer> pair = getBookService().getAll(
                page, Constants.Settings.BOOKS_ON_PAGE, search, orderBy, getLocales(), getLocale(request));

        request.setAttribute(Constants.Attributes.BOOKS, pair.getKey());
        request.setAttribute("count", pair.getValue());
        request.setAttribute("currPage", page);
        request.setAttribute("sortKeys", getBookService().getSortKeys().keySet());
      
        forward(PagesPaths.Main.Books.LIST, request, response);
    }
	
}
