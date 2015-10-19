package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.checkedOut;

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
 * Servlet for completing orders and returning books back to library.
 */
@WebServlet(Actions.Lib.CheckedOut.COMPLETE)
public class Complete extends LibraryServlet {

	private static final long serialVersionUID = 4679060267843341087L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order orderToComplete = getOrder(request, response);
        if (orderToComplete == null) {
            return;
        }
        request.setAttribute("order", orderToComplete);
        forward(PagesPaths.Lib.CheckedOut.COMPLETE, request, response);
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
        redirectToAction(Actions.Lib.CheckedOut.LIST, request, response);
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
        Order activeOrder = getOrderService().getById(parsedId);
        if (activeOrder == null || activeOrder.getType() != OrderType.CHECKED_OUT) {
            redirectBack(request, response);
            return null;
        }
        return activeOrder;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.orderNotFound");
        redirectToAction(Actions.Lib.CheckedOut.LIST, request, response);
    }

}
