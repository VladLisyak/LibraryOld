package ua.nure.lisyak.SummaryTask4.regularTasks;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nure.lisyak.SummaryTask4.service.UserService;
import ua.nure.lisyak.SummaryTask4.util.Constants;

/**
 * Task for updating subscriptions.
 */
public final class SubscriptionsUpdTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionsUpdTask.class);
    private UserService userServ;

    /**
     * Creates a new {@link SubscriptionsUpdTask}.
     *
     * @param context {@link ServletContext}
     */
    public SubscriptionsUpdTask(ServletContext context) {
        userServ = (UserService) context.getAttribute(Constants.Service.USER_SERVICE);
    }

    @Override
    public void run() {
        LOGGER.trace("Perform updating subscriptions...");
        userServ.updateSubs();
        LOGGER.trace("Subscriptions updating performed.");
    }

}
