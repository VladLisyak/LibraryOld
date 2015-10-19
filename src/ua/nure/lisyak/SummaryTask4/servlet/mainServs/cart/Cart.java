package ua.nure.lisyak.SummaryTask4.servlet.mainServs.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;



/**
 * Servlet for viewing cart and creating orders.
 */
@WebServlet(Actions.Common.Cart.CART)
public class Cart extends BaseMain {
	private static final long serialVersionUID = 3648630036067687939L;


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        List<Integer> idS = new ArrayList<>();
        idS.addAll(getOrderedBooks(request));
        if (idS.size() > 0) {
            List<Book> books = getBookService().getAll(idS);
            request.setAttribute(Constants.Attributes.BOOKS, books);
        }
        forward(PagesPaths.Main.Books.CART, request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = getUserService().getReaderById(getCurrentUser(request));
        List<Order> orders = getOrderService().add(reader, getOrderedBooks(request));
        if (orders == null) {
            setResult(request, false, "res.notEnoughBooksInStock");
            redirectToAction(Actions.Common.Cart.CART, request, response);
            return;
        }
        unsetOrderList(request);
        redirectToAction(Actions.Common.PROFILE, request, response);
    }

}
