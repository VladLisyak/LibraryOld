package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.readingRooms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.LibraryServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

import java.io.IOException;

/**
 * Servlet for viewing list of {@link Order}s made in reading rooms.
 */
@WebServlet(Actions.Lib.ReadingRooms.LIST)
public class List extends LibraryServlet {

	private static final long serialVersionUID = 523519596121347030L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.util.List<Order> orders = getOrderService().getAll(OrderType.READING_ROOM);
        request.setAttribute("orders", orders);
        getResult(request);
        forward(PagesPaths.Lib.ReadingRooms.LIST, request, response);
    }

}
