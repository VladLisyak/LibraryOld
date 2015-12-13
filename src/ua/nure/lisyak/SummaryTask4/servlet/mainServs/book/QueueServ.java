package ua.nure.lisyak.SummaryTask4.servlet.mainServs.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Queue;
import ua.nure.lisyak.SummaryTask4.service.QueueService;
import ua.nure.lisyak.SummaryTask4.service.servicesImpl.QueueServiceImpl;
import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet implementation class Queue
 */
@WebServlet("/queue")
public class QueueServ extends BaseMain {
	private static final QueueService qserv = new QueueServiceImpl();
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Item.class);
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = (Integer)request.getSession().getAttribute(Constants.Attributes.BOOK_ID);
		
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		
		Queue q = new Queue(bookId, userId);
		qserv.add(q);
		
		redirectToAction("/books", request, response);
	}
	
	

}
