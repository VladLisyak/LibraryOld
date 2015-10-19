package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.readingRooms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.LibraryServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for completing {@link Order}s that were made in 
 * reading rooms and returning the book back to library.
 */
@WebServlet(Actions.Lib.ReadingRooms.COMPLETE)
public class Complete extends LibraryServlet {

	private static final long serialVersionUID = 6305677925319431887L;

	@Override
    protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        Order orderToComplete = getOrder(requset, response);
        if (orderToComplete == null) {
            return;
        }
        requset.setAttribute("order", orderToComplete);
        forward(PagesPaths.Lib.ReadingRooms.COMPLETE, requset, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order orderToComplete = getOrder(request, response);
        if (orderToComplete == null) {
            return;
        }
        orderToComplete.setType(OrderType.COMPLETED);
        getOrderService().update(orderToComplete);
        setResult(request, true, "res.orderCompleted");
        redirectToAction(Actions.Lib.ReadingRooms.LIST, request, response);
    }

    private Order getOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String unparsedId = request.getParameter(Constants.Attributes.ID);
        if (unparsedId == null) {
            redirectBack(request, response);
            return null;
        }
        int parsedId;
        try {
            parsedId = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
            redirectBack(request, response);
            return null;
        }
        Order orderToGet = getOrderService().getById(parsedId);
        if (orderToGet == null || orderToGet.getType() != OrderType.READING_ROOM) {
            redirectBack(request, response);
            return null;
        }
        return orderToGet;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.orderNotFound");
        redirectToAction(Actions.Lib.ReadingRooms.LIST, request, response);
    }
}
