package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Constants.Attributes;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for unban users.
 */
@WebServlet(Actions.Admin.Users.UNBAN)
public class Unban extends DashboardServlet {

	private static final long serialVersionUID = 3950046553456696673L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usr = getUser(request, response);
        if (usr == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.USER, usr);
        forward(PagesPaths.Dashboard.Users.UNBAN, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usr = getUser(request, response);
        if (usr == null) {
            return;
        }
        usr.setRole(Role.USER);
        getUserService().update(usr);
        setResult(request, true, "res.userUnbanned");
        redirectToAction(Actions.Admin.Users.BLACK_LIST, request, response);
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
        } catch (NumberFormatException e) {
            redirectBack(request, response);
            return null;
        }
        User user = getUserService().getById(parsedId);
        if (user == null || user.getRole() != Role.BANNED) {
            redirectBack(request, response);
            return null;
        }
        return user;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.userNotFound");
        redirectToAction(Actions.Admin.Users.BLACK_LIST, request, response);
    }

}
