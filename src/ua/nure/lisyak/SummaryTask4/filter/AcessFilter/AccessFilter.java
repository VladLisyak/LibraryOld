package ua.nure.lisyak.SummaryTask4.filter.AcessFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.filter.BaseFilter;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;

import java.io.IOException;

/**
 * Defines whether the current {@link User} has access to the action or module.
 * If access can't be obtained, the User will be redirected 
 * to action that is allowed for current {@link Role} 
 */
@WebFilter("/*")
public class AccessFilter extends BaseFilter {
	
	private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);
	private static final String DEFAULT_REDIRECT = Actions.Common.HOME;
	
    private static final AccessConfiguration[] CONFIGS = new AccessConfiguration[]{
            new ActionConfig(
                    Role.USER,
                    Actions.Common.HOME,
                    Actions.Common.PROFILE,
                    Actions.Common.Cart.CART
            ),
            new ModuleConfig(
                    Role.ADMINISTRATOR,
                    Actions.Admin.DASHBOARD_INDEX,
                    Actions.Common.Books.LIST
            ),
            new ModuleConfig(
                    Role.LIBRARIAN,
                    Actions.Lib.LIBRARY_INDEX,
                    Actions.Common.Books.LIST
            )
    };
  

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	String path = request.getRequestURI().replace(request.getContextPath(), "");
    	LOGGER.debug("In Acess filter. "+ request.getRequestURI()+ " " + request.getContextPath()+ " "+ path);
    	HttpSession session = request.getSession();
    	String role =(String) session.getAttribute(Constants.Attributes.CURRENT_USER_ROLE);
    	
    	User usr = (User) session.getAttribute(role);
    	
        String redirect = isAllowed(path, usr);
        
        LOGGER.debug((usr!=null ? usr.getRole() : "") + path);
        if (redirect != null) {
        	LOGGER.debug("Redirection performed to " +redirect);
            response.sendRedirect(request.getContextPath()+redirect);
            return;
        }
        chain.doFilter(request, response);
    }

    
    /**
     * Defines redirection path if current user can't access to specified path.
     */
    private String isAllowed(String path, User user) {
        if(user!=null){
            if (user.getRole().equals(Role.OVERDUE)&& path.equals(Actions.Common.PROFILE)){
                return null;
            }
            if (user.getRole().equals(Role.BANNED)){
                if(!path.equals(Actions.Common.HOME)){
                  return Actions.Common.HOME;
                }else{
                  return null;
                }
                
            }
        }

        for (AccessConfiguration config : CONFIGS) {
            if (config.belongs(path)){
            	if (config.userRoleMatch(user)){
            	  return config.isAllowed(user, path);}
            	return DEFAULT_REDIRECT;
            }
        }
        return null;
    }

}
