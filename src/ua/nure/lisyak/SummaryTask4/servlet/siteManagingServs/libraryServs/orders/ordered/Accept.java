package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.ordered;

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
import ua.nure.lisyak.SummaryTask4.validation.OrderValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

/**
 * Servlet for accepting {@link Orders}
 */
@WebServlet(Actions.Lib.Ordered.ACCEPT)
public class Accept extends LibraryServlet {

	private static final long serialVersionUID = 5074169649853521842L;

	private static final String DAYS = "days";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = getOrder(request, response);
        if (order == null) {
            return;
        }
        request.setAttribute("order", order);
        request.setAttribute("max", Constants.Settings.MAX_CHECK_OUT_PERIOD);
        forward(PagesPaths.Lib.Ordered.ACCEPT, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order orderToCheckOut = getOrder(request, response);
        if (orderToCheckOut == null) {
            return;
        }
        Integer days = getIntParam(request, DAYS);
        Validator orderValidator = new OrderValidator(days, getInterp(), getLocale(request));
        if (orderValidator.hasErrors()) {
            forwardBack(request, response, orderValidator, days);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DATE, days);
        Date dueDate = new Date(calendar.getTime().getTime());
        orderToCheckOut.setDueDate(dueDate);
        orderToCheckOut.setType(OrderType.CHECKED_OUT);
        getOrderService().update(orderToCheckOut);
        setResult(request, true, "result.orderAccepted");
        redirectToAction(Actions.Lib.Ordered.LIST, request, response);
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
        Order orderToCheckOut = getOrderService().getById(parsedId);
        if (orderToCheckOut == null || orderToCheckOut.getType() != OrderType.ORDERED) {
            redirectBack(request, response);
            return null;
        }
        return orderToCheckOut;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.orderNotFound");
        redirectToAction(Actions.Lib.Ordered.LIST, request, response);
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, Integer days)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute(DAYS, days);
        forward(PagesPaths.Lib.Ordered.ACCEPT, request, response);
    }

}
