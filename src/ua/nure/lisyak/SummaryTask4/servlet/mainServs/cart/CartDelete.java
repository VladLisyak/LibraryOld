package ua.nure.lisyak.SummaryTask4.servlet.mainServs.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Constants.Attributes;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;


/**
 * Servlet for deleting book from cart.
 */
@WebServlet(Actions.Common.Cart.DELETE_ITEM)
public class CartDelete extends BaseMain {
	private static final long serialVersionUID = -3542579153370506014L;

	  @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        Integer id = getIntParam(request, Attributes.ID);
	        if (id != null) {
	        	request.getSession().setAttribute(Constants.Attributes.ALREADY_ORDERED, false);
	
	            updateOrderList(request, id, 0).size();
	        }
	    }
}
