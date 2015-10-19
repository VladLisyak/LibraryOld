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
 * Servlet for viewing a list of {@link Reader}s with overdue {@link Subscription}s.
 */
@WebServlet(Actions.Admin.Users.OVERDUE)
public class OverdueList extends DashboardServlet {

	private static final long serialVersionUID = 3630569037062697149L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        request.setAttribute(Constants.Attributes.USERS, getUserService().getByRole(Role.OVERDUE));
        forward(PagesPaths.Dashboard.Users.OVERDUE_LIST, request, response);
    }
}
