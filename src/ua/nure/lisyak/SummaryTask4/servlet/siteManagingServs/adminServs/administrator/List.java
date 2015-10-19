package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet for viewing a list of {@link Administrator}s.
 */
@WebServlet(Actions.Admin.Admins.ADMINISTRATORS_PATH)
public class List extends DashboardServlet {

	private static final long serialVersionUID = -7576948354909765186L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        request.setAttribute("administrators", getUserService().getByRole(Role.ADMINISTRATOR));
        forward(PagesPaths.Dashboard.Admins.LIST, request, response);
    }

}
