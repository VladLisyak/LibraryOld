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
 * Servlet for viewing {@link Role#BANNED} users list.
 */
@WebServlet(Actions.Admin.Users.BLACK_LIST)
public class BlackList extends DashboardServlet {

	private static final long serialVersionUID = 5329527290573837906L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        request.setAttribute(Constants.Attributes.USERS, getUserService().getByRole(Role.BANNED));
        forward(PagesPaths.Dashboard.Users.BLACK_LIST, request, response);
    }

}
