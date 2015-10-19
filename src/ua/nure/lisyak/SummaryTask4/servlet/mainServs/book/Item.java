package ua.nure.lisyak.SummaryTask4.servlet.mainServs.book;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.OrderDetails;
import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for viewing a {@link Book}.
 */
@WebServlet(Actions.Common.Books.ITEM)
public class Item extends BaseMain {
   
	private static final long serialVersionUID = 1476020315043419104L;
	private static final Logger LOGGER = Logger.getLogger(Item.class);
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = getBook(request, response);
        if (book == null) { return;}
        
        User usr = getCurrentUser(request);
        request.setAttribute(Constants.Attributes.ALREADY_ORDERED, false);
        
        alreadyOrdered(request, usr, book);
        
        request.setAttribute(Constants.Attributes.BOOK, book);
        forward(PagesPaths.Main.Books.ITEM, request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = getBook(request, response);
        if (book == null) {
            return;
        }
        LOGGER.debug(book.getTitle().value("ru"));
        updateOrderList(request, book.getId(), 1);
        redirectToAction(Actions.Common.Books.ITEM + "?id=" + book.getId(), request, response);

    }

    /**
     * Retrieves book from request.
     * @return retrieved {@link Book} entity
     * @throws ServletException
     * @throws IOException
     */
    private Book getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIntParam(request, Constants.Attributes.ID);
        if (id == null) {
            redirectBack(request, response);
            return null;
        }
        Book book = getBookService().getById(id);
        if (book == null) {
            redirectBack(request, response);
            return null;
        }
        return book;
    }

    /**
     * Defines if current book contains in specified list of orders.
     * @param orders list of {@link Order}s to look at.
     * @param book the {@link Book} to look for.
     * @return {@code true} if book contains in list, {@code false} otherwise.
     */
    private boolean containsIn(List<OrderDetails> orders, Book book){
        for (OrderDetails order : orders) {
        	if (order.getBook().equals(book)){
				return true;
			}
		}	
        return false;
    }
    
    /**
     * Defines if specified {@link Book} contains among 
     * ordered or checked out books of this user, or in its cart. 
     * @param request {@link HttpServletRequest}
     * @param usr {@link User} among whose books it is need to look for.
     * @param book {@link Book} to look for. 
     */
    private void alreadyOrdered(HttpServletRequest request, User usr, Book book){
    	if (usr != null && usr.getRole().equals(Role.USER)){
	        Reader reader = getUserService().getReaderById(usr);
	        request.setAttribute("ordered", getOrderService().getOrdered(reader));
	        
	        boolean containsInCheckedOut = containsIn(getOrderService().getCheckedOut(reader), book);
	        boolean containsInOrdered = containsIn(getOrderService().getOrdered(reader), book);
	        
	        if (containsInCheckedOut 
	        	|| containsInOrdered
	        	|| getOrderedBooks(request).contains(book.getId())){
	        	request.setAttribute(Constants.Attributes.ALREADY_ORDERED, true);
	        }
        }
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	redirectToAction(Actions.Common.Books.LIST, request, response);
    }
    
}
