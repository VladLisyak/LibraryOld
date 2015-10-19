package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet for viewing home page of module.
 */
@WebServlet(Actions.Lib.LIBRARY_INDEX)
public class Home extends LibraryServlet {

	private static final long serialVersionUID = 1334609140902477520L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respresponse) throws ServletException, IOException {
        request.setAttribute("ordersCount", getOrderService().countByType(OrderType.ORDERED));
        request.setAttribute("checkOutsCount", getOrderService().countByType(OrderType.CHECKED_OUT));
        request.setAttribute("completedCount", getOrderService().countByType(OrderType.COMPLETED));
        request.setAttribute("readingRoomsCount", getOrderService().countByType(OrderType.READING_ROOM));
        forward(PagesPaths.Lib.INDEX, request, respresponse);     
    }

}
