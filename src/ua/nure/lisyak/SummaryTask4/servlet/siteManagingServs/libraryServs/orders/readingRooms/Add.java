package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.orders.readingRooms;


import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs.LibraryServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.OrderValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Servlet for Checking out books to reading rooms.
 */
@WebServlet(Actions.Lib.ADD_ORDER)
public class Add extends LibraryServlet {
   
	private static final long serialVersionUID = 8142830632176247135L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(PagesPaths.Lib.ADD_ORDER, request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order orderToAdd = getOrder(request, response);
        if (orderToAdd == null) {
            return;
        }
        getOrderService().add(orderToAdd);
        setResult(request, true, "res.bookCheckedOut");
        redirectToAction(Actions.Lib.ReadingRooms.LIST, request, response);
    }


    private Order getOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIntParam(request, "bookId");
        Integer subscriptionId = getIntParam(request, "subscriptionId");
        Validator orderValidator = new OrderValidator(id, subscriptionId, getInterp(), getLocale(request));
        if (orderValidator.hasErrors()) {
            forwardBack(request, response, orderValidator, id, subscriptionId);
            return null;
        }
        Order orderToGet = new Order();
        orderToGet.setBookId(id);
        orderToGet.setSubscriptionId(subscriptionId);
        orderToGet.setType(OrderType.READING_ROOM);
        orderToGet.setDueDate(new Date(new java.util.Date().getTime()));
        if (!getBookService().exists(id)) {
            orderValidator.putIssue("bookId", "validator.bookNotFound");
            forwardBack(request, response, orderValidator, id, subscriptionId);
            return null;
        }
        if (getBookService().getById(id).getInStock()<1){
        	orderValidator.putIssue("bookId", "validator.stock");
            forwardBack(request, response, orderValidator, id, subscriptionId);
            return null;
        }
        if (!getUserService().readerExists(subscriptionId)) {
            orderValidator.putIssue("subscriptionId", "validator.userNotFound");
            forwardBack(request, response, orderValidator, id, subscriptionId);
            return null;
        }
        return orderToGet;
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, Integer bookId, Integer subscriptionId)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute("bookId", bookId);
        request.setAttribute("subscriptionId", subscriptionId);
        forward(PagesPaths.Lib.ADD_ORDER, request, response);
    }

}
