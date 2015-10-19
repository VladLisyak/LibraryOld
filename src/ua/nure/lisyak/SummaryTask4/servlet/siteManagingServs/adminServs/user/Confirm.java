package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.user;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Constants.Attributes;
import ua.nure.lisyak.SummaryTask4.util.mailServ.MailService;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;



/**
 * Servlet for confirmation of registered users.
 */
@WebServlet(Actions.Admin.Users.CONFIRM)
public class Confirm extends DashboardServlet {
	private static final long serialVersionUID = 4120856940966376112L;

	private static final Logger LOGGER = Logger.getLogger(Confirm.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request, response);
        if (user == null){
            return;
        }
        request.setAttribute(Constants.Attributes.USER, user);
        forward(PagesPaths.Dashboard.Users.CONFIRM, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request, response);
        if (user == null){
            return;
        }
        
        getUserService().confirm(user);
        try {
            sendEmail(request, user);
        } catch (MessagingException e) {
            LOGGER.warn("Message was not sent.", e);
            return;
        }
        setResult(request, true, "res.userConfirmed");
        redirectToAction(Actions.Admin.Users.NOT_CONFIRMED, request, response);
    }

    /**
     * Send confirmation message, to email, specified in particular {@link User} entity
     * 
     * @param user to whose email to be sent
     * @throws MessagingException
     */
    private void sendEmail(HttpServletRequest request, User user) throws MessagingException {
        String locale = getLocale(request);
        String sendTo = user.getEmail();
        String template = MailService.getTemplate("/mailTemp/userConfirmed.html");
        String title = getInterp().translate("mail.userConfirmed.title", locale);
        template = template
                .replace("${language}", locale)
                .replace("${title}", title)
                .replace("${body}", getInterp().translate("mail.userConfirmed.body", locale))
                .replace("${link}", "localhost:8080/")
                .replace("${linkText}", getInterp()
                		.translate("mail.userConfirmed.account", locale).toLowerCase());
        MailService.send(sendTo, title, template);
    }

    private User getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String unparsedId = request.getParameter(Attributes.ID);
        if (unparsedId == null) {	
            redirectBack(request, response);
            return null;
        }
        
        int parsedId;
        try {
            parsedId = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e){
        	
            redirectBack(request, response);
            return null;
        }
        User user = getUserService().getById(parsedId);
        if (user == null || user.getRole() != Role.NEEDS_CONFIRMATION) {
            redirectBack(request, response);
            return null;
        }
        return user;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.userNotFound");
        redirectToAction(Actions.Admin.Users.NOT_CONFIRMED, request, response);
    }

}
