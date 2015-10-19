package ua.nure.lisyak.SummaryTask4.servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import ua.nure.lisyak.SummaryTask4.service.AuthorService;
import ua.nure.lisyak.SummaryTask4.service.BookService;
import ua.nure.lisyak.SummaryTask4.service.OrderService;
import ua.nure.lisyak.SummaryTask4.service.UserService;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic servlet for all servlets. Provides access to 
 * services that are needed for more than one servlet.
 */
public abstract class AbstractServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7053877476429245836L;
    private Interpreter interp;
    private OrderService orderService;
    private UserService userService;
    private BookService bookService;
    private AuthorService authorService;
    private String defaultLocale;
    private String[] locales;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute(Constants.Service.USER_SERVICE);
        orderService = (OrderService) context.getAttribute(Constants.Service.ORDER_SERVICE);
        bookService = (BookService) context.getAttribute(Constants.Service.BOOK_SERVICE);
        authorService = (AuthorService) context.getAttribute(Constants.Service.AUTHOR_SERVICE);
        interp = (Interpreter) context.getAttribute(Constants.Attributes.TRANSLATOR);
        defaultLocale = (String) context.getAttribute(Constants.Attributes.DEFAULT_LOCALE);
        locales = (String[]) context.getAttribute(Constants.Attributes.LOCALES);
        
    }

    protected Interpreter getInterp() {
        return interp;
    }
    
    protected String getDefaultLocale() {
        return defaultLocale;
    }

    public OrderService getOrderService() {
		return orderService;
	}

	protected String[] getLocales() {
         return locales;
    }

    protected UserService getUserService() {
       return userService;
    }
    
    protected BookService getBookService() {
    	 return bookService;
    }
    
    protected AuthorService getAuthorService() {
       return authorService;
    }
    
    protected String getLocale(HttpServletRequest request) {
        return (String)request.getAttribute(Constants.Attributes.CURRENT_LOCALE);
    }
    
    /**
     * Retrieves specified integer parameter from request. 
     * @param param The name of parameter to retrieve
     * @return int value of parameter
     */
    protected Integer getIntParam(HttpServletRequest request, String param) {
        String paramValue = request.getParameter(param);
        if (paramValue == null) {
            return null;
        }
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Retrieves integer parameter from request. 
     * Sets to default if parameter is null
     * @param request HttpServletRequest
     * @param param The name of parameter to retrieve
     * @param defaultValue value to set if value from request is null.
     * @return int value of parameter.
     */
    protected int getIntParam(HttpServletRequest request, String param, int defaultValue) {
        Integer value = getIntParam(request, param);
        if (value == null || value < 1) {
             return defaultValue;
        }
        return value;
    }

    /**
     * Retrieves specified integer parameters from request. 
     * @param param The name of parameter to retrieve
     * @return <{@link List} list of integer values
     */
    protected List<Integer> getIntParamValues(HttpServletRequest request, String param) {
        String[] values = request.getParameterValues(param);
        if (values == null) {
            return null;
        }
        List<Integer> intValues = new ArrayList<>();
        for (String value : values) {
            try {
                Integer intVal = Integer.parseInt(value);
                intValues.add(intVal);
            } catch (NumberFormatException e) {
                intValues.add(null);
            }
        }
        return intValues;
    }

    /**
     * Retrieves specified String parameter from request. 
     * @param param The name of parameter to retrieve
     * @return {@link String} value of parameter
     */
    protected String getStringParam(HttpServletRequest request, String param) {
        String paramValue = request.getParameter(param);
        if (paramValue == null) {
            return null;
        }
        return paramValue.trim();
    }

    /**
     * Retrieves String parameter from request. 
     * Sets to default if parameter is null
     * @param request HttpServletRequest
     * @param param The name of parameter to retrieve
     * @param defaultValue value to set if value from request is null.
     * @return String value of parameter.
     */
    protected String getStringParam(HttpServletRequest request, String paramName, String defaultValue) {
        String paramValue = getStringParam(request, paramName);
        if (paramValue == null || paramValue.isEmpty()) {
            return defaultValue;
        }
        return paramValue;
    }

}
