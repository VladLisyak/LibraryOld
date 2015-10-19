package ua.nure.lisyak.SummaryTask4.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.fileProcessing.FileService;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Basic servlet that provides functionality for operating 
 * with current user. And safe retrieving of parameters.
 */
public abstract class BaseServlet extends AbstractServlet {
	private static final Logger LOGGER = Logger.getLogger(BaseServlet.class);

	private static final long serialVersionUID = -9037428387637041940L;
    private FileService fileService;

    @Override
    public void init() throws ServletException {
    	LOGGER.info("Basic servlet init started.");
        super.init();
        fileService = (FileService) getServletContext().getAttribute(Constants.Service.FILE_PROC_SERVICE);
        LOGGER.info("Basic servlet init finished.");
    }

    protected FileService getFileService() {
        return fileService;
    }

    protected User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currUserRole = (String)session.getAttribute(Constants.Attributes.CURRENT_USER_ROLE);
        Object userObj = session.getAttribute(currUserRole);
        
        return userObj == null ? null : (User) userObj;
    }

    /**
     * Set current user in {@link HttpSession}
     * @param request
     * @param user user to set
     */
    protected void setCurrentUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.Attributes.CURRENT_USER_ROLE, user.getRole().toString().toLowerCase());
        session.setAttribute(user.getRole().toString().toLowerCase(), user);
    }

    /**
     * Unset current user in {@link HttpSession}
     * @param request
     */
    protected void unsetLoggedUser(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        String currUserRole = (String)session.getAttribute(Constants.Attributes.CURRENT_USER_ROLE);
        session.removeAttribute(currUserRole);
        session.removeAttribute(Constants.Attributes.CURRENT_USER_ROLE);
    }

    protected void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void redirectToAction(String uri, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath()+uri);
    }

    /**
     * Gets interpretation of some value into available languages. 
     * @param paramName name of parameter to get
     * @return {@link Interpretation} object for specified value
     */
    protected Interpretation getInterpretation(HttpServletRequest request, String paramName) {
        Interpretation interpretation = new Interpretation();
        for (String locale : getLocales()) {
            String value = getStringParam(request, paramName + "_" + locale);
            setValue(interpretation, locale, value);
        }
        return interpretation;
    }

    /**
     * Sets interpretation of value into specified locale
     * @param interp {@link Interpretation} object
     * @param locale locale of specified value
     * @param value interpretation of expression in current locale
     */
    private void setValue(Interpretation interp, String locale, String value) {
        try {
            Field field = Interpretation.class.getDeclaredField(locale);
            field.setAccessible(true);
            field.set(interp, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.warn("Unable to set translation value.", e);
        }
    }
    /**
     * Checks if the specified login unique
     * @param usr {@link User} to check
     * @param validator {@link Validator} entity for testing.
     */
    protected void checkUniqueness(User usr, Validator validator) {
        User existingUser = getUserService().getByLogin(usr.getLogin(), usr.getId());
        if (existingUser != null){
        	
            validator.putIssue("login", "validator.loginTaken");
        }
        existingUser = getUserService().getByEmail(usr.getEmail(), usr.getId());
        if (existingUser != null){
        	
            validator.putIssue("email", "validator.emailTaken");
        }
    }

}
