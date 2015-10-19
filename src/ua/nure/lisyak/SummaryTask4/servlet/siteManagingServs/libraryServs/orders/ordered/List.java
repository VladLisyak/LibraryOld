package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.ordered;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.LibraryServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for viewing a list of ordered {@link Book}s.
 */
@WebServlet(Actions.Lib.Ordered.LIST)
public class List extends LibraryServlet {

	private static final long serialVersionUID = 1935672349332252451L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.util.List<Order> orders = getOrderService().getAll(OrderType.ORDERED);
        request.setAttribute("orders", orders);
        getResult(request);
        forward(PagesPaths.Lib.Ordered.LIST, request, response);
    }

}
