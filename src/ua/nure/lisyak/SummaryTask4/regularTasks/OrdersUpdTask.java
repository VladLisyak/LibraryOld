package ua.nure.lisyak.SummaryTask4.regularTasks;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nure.lisyak.SummaryTask4.service.OrderService;
import ua.nure.lisyak.SummaryTask4.util.Constants;

/**
 * Task for updating orders.
 */
public final class OrdersUpdTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersUpdTask.class);
    private OrderService orderServ;

    /**
     * Creates a new {@link OrdersUpdTask}.
     *
     * @param context {@link ServletContext}
     */
    public OrdersUpdTask(ServletContext context) {
        orderServ = (OrderService) context.getAttribute(Constants.Service.ORDER_SERVICE);
    }

    @Override
    public void run() {
        LOGGER.trace("Perform updating orders.");
        orderServ.updateOrders();
        LOGGER.trace("Updating performed.");
    }

}
