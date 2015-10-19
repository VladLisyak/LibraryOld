package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for viewing a list of all users that needs confirmation.
 */
@WebServlet(Actions.Admin.Users.NOT_CONFIRMED)
public class NotConfirmedList extends DashboardServlet {

	private static final long serialVersionUID = 1814683886640334926L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        request.setAttribute(Constants.Attributes.USERS, getUserService().getByRole(Role.NEEDS_CONFIRMATION));
        forward(PagesPaths.Dashboard.Users.NOT_CONFIRMED, request, response);
    }
}
