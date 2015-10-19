package ua.nure.lisyak.SummaryTask4.servlet.authorizationServs;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.*;


/**
 * Servlet implementation of Main Page that is also an authorization Page
 */
@WebServlet(name = "HomeServ", urlPatterns = {"/index"})
public class HomeAndLogin extends BaseMain {

	private static final Logger LOGGER = Logger.getLogger(HomeAndLogin.class);
	
	private static final long serialVersionUID = -5836443845068780386L;

	@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			User user = getCurrentUser(request);
	        if (user != null) {
	        	LOGGER.trace(user.getRole() + " access");
	        	int redirectionIndex = user.getRole().ordinal();	        	
	        	
	        	if(redirectionIndex == Role.ADMINISTRATOR.ordinal()){
	        	   redirectToAction(Actions.Admin.DASHBOARD_INDEX, request, response);
	        	   return;
	        	}
	        	
	        	if(redirectionIndex == Role.LIBRARIAN.ordinal()){
	        	   redirectToAction(Actions.Lib.LIBRARY_INDEX, request, response);
	        	   return;
	        	}
	        	
	            redirectToAction(Actions.Common.Books.LIST, request, response);
	            return;
	        }
	        LOGGER.trace("Unautorized user access");
	        
	        forward(PagesPaths.Main.LOGIN, request, response);
	    }
	
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	if (getCurrentUser(request)!=null){
	        	LOGGER.debug("User already logged in");
	        	
	        	redirectToAction(Actions.Common.Books.LIST, request, response);
	        	return;
	        }
	    	String login = getStringParam(request, "login");
	        String password = getStringParam(request, "password");
	        String locale = getLocale(request);
	      
	        Validator validator = new UserValidator(login, password, getInterp(), locale);
	        if (validator.hasErrors()) {
	            setErrors(request, response, validator, login, password);
	            return;
	        }
	        
	        User user = getUserService().getByLogin(login);
	        if (user == null) {
	            validator.putIssue("login", "validator.invalidLogin");
	            setErrors(request, response, validator, login, password);
	            return;
	        }
	        LOGGER.debug("User found. The role is "+ user.getRole()+ "and login is "+ user.getLogin());
	        
	        if (!user.getPassword().equals(password)) {
	            validator.putIssue("password", "validator.invalidPassword");
	            setErrors(request, response, validator, login, password);
	            return;
	        }
	        
	        if (!user.getRole().equals(Role.NEEDS_CONFIRMATION)){
	        setCurrentUser(request, user);}
	        switch (user.getRole()) {
				case ADMINISTRATOR:{
					redirectToAction(Actions.Admin.DASHBOARD_INDEX, request, response);
					break;}
				case LIBRARIAN:{
					redirectToAction(Actions.Lib.LIBRARY_INDEX, request, response);
					break;
				}
				case BANNED:{
					unsetLoggedUser(request);
					request.setAttribute("banned", true);
					forward(PagesPaths.Main.LOGIN, request, response);
					break;
				}
				default:
					redirectToAction(Actions.Common.Books.LIST, request, response);
					break;
				}
	    }

	    /**
	     * Sets errors which prevents logging and 
	     * forward back to validation form 
	     * 
	     * @param validator {@link Validator} entity that contains errors to be set.
	     * @param login login of account that the user trying to get access to.
	     * @param password password of account that the user trying to get access to
	     * 
	     * @throws ServletException
	     * @throws IOException
	     */
	    private void setErrors(HttpServletRequest request, HttpServletResponse response, Validator validator, String login, String password)
	            throws ServletException, IOException {
	        request.setAttribute("messages", validator.getMessages());
	        
	        LOGGER.trace("Errors found while logging in. With login " + login + ". Forwarding back.");
	        
	        request.setAttribute("login", login);
	        request.setAttribute("password", password);
	        
	        forward(PagesPaths.Main.LOGIN, request, response);
	    }

}
