package ua.nure.lisyak.SummaryTask4.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.regularTasks.OrdersUpdTask;
import ua.nure.lisyak.SummaryTask4.regularTasks.SubscriptionsUpdTask;
import ua.nure.lisyak.SummaryTask4.util.Constants;

/**
 * Initializer for task that should be performed regularly.
 */
@WebListener()
public class RegularTasksListener implements ServletContextListener{
	private static final Logger LOGGER = Logger.getLogger(RegularTasksListener.class);
    private ScheduledExecutorService executorServ;

    
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	
        ServletContext context = event.getServletContext();
        long delay = getPeriod(Constants.Settings.UPDATE_ORDERS_TASK_EXECUTION_TIME);
        executorServ = Executors.newSingleThreadScheduledExecutor();
        Runnable updateOrders = new OrdersUpdTask(context);
        Runnable updateSubscriptions = new SubscriptionsUpdTask(context);
        executorServ.schedule(updateOrders, 0, TimeUnit.MILLISECONDS);
        executorServ.schedule(updateSubscriptions, 0, TimeUnit.MILLISECONDS);
        executorServ.schedule(new RegularTask(updateOrders, updateSubscriptions), delay, TimeUnit.MILLISECONDS);
        LOGGER.info("Daily tasks set up.");
    }

    /**
     * Gets the time of next updating.
     * @param time time of next updating 
     * @return time to next updating
     */
    private long getPeriod(String time) {
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat dateTimeForm = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        
        Date date = new Date();
        Date current = date;
        String formattedDate = dateForm.format(date);
        String dateToParse = formattedDate + " " + time;
            try {
				date = dateTimeForm.parse(dateToParse);
			} catch (ParseException e) {
	            LOGGER.warn("Unable parse date", e);
	            return 0;
	        }
        
        if (current.getTime() > date.getTime()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            date = calendar.getTime();
        }
        return date.getTime() - current.getTime();
    }

    private final class RegularTask implements Runnable {

        private Runnable[] tasks;

        private RegularTask(Runnable... tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            for (Runnable task : tasks) {
                executorServ.scheduleAtFixedRate(task, 0, 1, TimeUnit.DAYS);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	executorServ.shutdownNow();
    }
}

