package ua.nure.lisyak.SummaryTask4.servlet.authorizationServs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.servlet.BaseServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;

/**
 * Servlet that is intended for unsetting 
 * current {@link User} in session. 
 */
@WebServlet(Actions.Common.LOGOUT)
public class Logout extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		unsetLoggedUser(request);
		redirectToAction(Actions.Common.HOME, request, response);
	}

}
