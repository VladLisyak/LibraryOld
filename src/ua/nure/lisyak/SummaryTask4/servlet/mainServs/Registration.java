package ua.nure.lisyak.SummaryTask4.servlet.mainServs;


import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.mailServ.MailService;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.UserValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Servlet for registration of new readers.
 */
@WebServlet(Actions.Common.REGISTRATION)
public class Registration extends BaseMain {

	private static final long serialVersionUID = 1380327659381685765L;
	
	private static final Logger LOGGER = Logger.getLogger(Registration.class);

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (getCurrentUser(request) != null) {
            redirectToAction(Actions.Common.PROFILE, request, response);
            return;
        }
        
        String confirmation = getStringParam(request, Constants.Attributes.EMAIL_CONFIRMATION_PARAMETER); 
        if (confirmation == null) {
        	forward(PagesPaths.Main.REGISTRATION, request, response);
        	return;
        }
           
        String encodedEmail = getStringParam(request, Constants.Attributes.EMAIL_CONFIRMATION_PARAMETER);
        String decodedEmail = new String(Base64.getDecoder().decode(encodedEmail), StandardCharsets.UTF_8);
        User usr = getUserService().getByEmail(decodedEmail, 0);
        if(usr!=null){
	      getUserService().confirm(usr);
	      request.setAttribute(Constants.Attributes.REGISTRATION_SUCCEED, true);
	      forward(PagesPaths.Main.LOGIN, request, response);
	      return;
	    } 
        
        redirectToAction(PagesPaths.Main.REGISTRATION, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(getStringParam(request, "firstName"));
        user.setLastName(getStringParam(request, "lastName"));
        user.setEmail(getStringParam(request, "email"));
        user.setLogin(getStringParam(request, "login"));
        user.setPassword(getStringParam(request, "password"));
        String locale = getLocale(request);
        Validator validator = new UserValidator(user, getInterp(), locale);
        if (validator.hasErrors()) {
        	
            forwardBack(request, response, validator, user);
            return;
        }
        
        checkUniqueness(user, validator);
        if (validator.hasErrors()) {
        	
            forwardBack(request, response, validator, user);
            return;
        }
        User addedUser = getUserService().add(user);
        
        if (addedUser == null) {
            validator.putIssue("error", "validator.error500");
            forwardBack(request, response, validator, user);
            return;
        }
        HttpSession session = request.getSession();
        
        try {
        	LOGGER.info("Sending confirmaton letter.");
			sendEmail(request, user);
			LOGGER.info("Srnding is done!");
			session.setAttribute(Constants.Attributes.REGISTRATION_SUCCEED, true);
		} catch (MessagingException e) {
			session.setAttribute(Constants.Attributes.REGISTRATION_SUCCEED, false);
			redirectToAction(Actions.Common.Books.LIST, request, response);
			LOGGER.warn("Unable to send confirmation message. Account should be confirmed manually.");
		}
        redirectToAction(Actions.Common.Books.LIST, request, response);
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, User user)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute(Constants.Attributes.USER, user);
        forward(PagesPaths.Main.REGISTRATION, request, response);
    }
    
    private void sendEmail(HttpServletRequest request, User user) throws MessagingException{
        String locale = getLocale(request);
        String to = user.getEmail();
        String template = MailService.getTemplate("/mailTemp/userConfirmed.html");
        String title = getInterp().translate("mail.userConfirm.title", locale);
        template = template
                .replace("${language}", locale)
                .replace("${title}", title)
                .replace("${body}", getInterp().translate("mail.userConfirm.body", locale))
                .replace("${link}", generateRefference(request, to))
                .replace("${linkText}", getInterp().translate("mail.userConfirm.reff", locale).toLowerCase());
        LOGGER.debug(template);
        MailService.send(to, title, template);
    }
    
    private String generateRefference(HttpServletRequest request, String to){
    	StringBuilder sb = new StringBuilder(request.getRequestURL());
    	String encodedEmail = new String(Base64.getEncoder().encode(to.getBytes(StandardCharsets.UTF_8)));
    	return sb.append("?")
    			.append(Constants.Attributes.EMAIL_CONFIRMATION_PARAMETER)
    			.append("=")
    			.append(encodedEmail).toString();
    }
}
