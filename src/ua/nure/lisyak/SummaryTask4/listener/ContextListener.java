package ua.nure.lisyak.SummaryTask4.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.HikariConnManager;
import ua.nure.lisyak.SummaryTask4.db.dao.AuthorDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.BookDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.OrderDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.PublisherDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.SubscriptionDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.InterpretationDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.UserDao;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.AuthorDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.BookDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.OrderDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.PublisherDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.SubscriptionDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.InterpretationDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.UserDAOImpl;
import ua.nure.lisyak.SummaryTask4.service.AuthorService;
import ua.nure.lisyak.SummaryTask4.service.BookService;
import ua.nure.lisyak.SummaryTask4.service.OrderService;
import ua.nure.lisyak.SummaryTask4.service.PublisherService;
import ua.nure.lisyak.SummaryTask4.service.InterpretationService;
import ua.nure.lisyak.SummaryTask4.service.UserService;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.AuthorServiceImpl;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.BookServiceImpl;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.OrderServiceImpl;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.PublisherServiceImpl;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.InterpretationServiceImpl;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.UserServiceImpl;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.fileProcessing.FileService;
import ua.nure.lisyak.SummaryTask4.util.fileProcessing.FileServiceImpl;
/**
 * Context listener. Initializes services, and locales for future use.
 *
 */
@WebListener()
public class ContextListener implements ServletContextListener{
	private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
    

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOGGER.trace("Context initialization started.");
		
		AuthorService authServ = new AuthorServiceImpl();
		BookService bookServ = new BookServiceImpl();
	    OrderService orderServ = new OrderServiceImpl();
		PublisherService pubServ = new PublisherServiceImpl();
		InterpretationService translServ = new InterpretationServiceImpl();
		UserService userServ = new UserServiceImpl();
		FileService fileServ = new FileServiceImpl(Constants.ROUTES.UPLOAD_DIR);
		
		String[] locales = servletContextEvent.getServletContext().getInitParameter("locales").split(" ");
		
		servletContextEvent.getServletContext().setAttribute(Constants.Service.AUTHOR_SERVICE, authServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Service.BOOK_SERVICE, bookServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Service.ORDER_SERVICE, orderServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Service.PUBLISHER_SERVICE, pubServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Service.ITERPRETATION_SERVICE, translServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Service.USER_SERVICE, userServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Service.FILE_PROC_SERVICE, fileServ);
        servletContextEvent.getServletContext().setAttribute(Constants.Attributes.LOCALES, locales);
        
        AuthorDAO authDAO = new AuthorDAOImpl();
        BookDAO bookDAO = new BookDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        PublisherDAO pubDAO = new PublisherDAOImpl();
        SubscriptionDAO subsDAO = new SubscriptionDAOImpl();
        InterpretationDAO translDAO = new InterpretationDAOImpl();
        UserDao userDAO = new UserDAOImpl();
        
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.AUTHOR_DAO, authDAO);
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.BOOK_DAO, bookDAO);
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.ORDER_DAO, orderDAO);
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.PUBLISHER_DAO, pubDAO);
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.TRANSLATION_DAO, translDAO);
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.USER_DAO, userDAO);
        servletContextEvent.getServletContext().setAttribute(Constants.DAO.SUBSCRIPTION_DAO, subsDAO);
        
    	LOGGER.trace("Context initialization finished.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		HikariConnManager.stop();
		LOGGER.trace("Context listener destoyed.");
	}
}
