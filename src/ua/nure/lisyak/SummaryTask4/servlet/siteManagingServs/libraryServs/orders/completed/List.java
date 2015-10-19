package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.completed;

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
 * Servlet for viewing a list of completed {@link Order}s.
 */
@WebServlet(Actions.Lib.Completed.LIST)
public class List extends LibraryServlet {

	private static final long serialVersionUID = -5528120229484650694L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.util.List<Order> orders = getOrderService().getAll(OrderType.COMPLETED);
        request.setAttribute("orders", orders);
        getResult(request);
        forward(PagesPaths.Lib.Completed.LIST, request, response);
    }

}
