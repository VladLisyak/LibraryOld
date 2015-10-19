package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.librarian;

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
 * Servlet for viewing a list of {@link Librarian}s.
 */
@WebServlet(Actions.Admin.Librarians.LIBRARIANS_PATH)
public class List extends DashboardServlet {

	private static final long serialVersionUID = 3791436686957222508L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        request.setAttribute("librarians", getUserService().getByRole(Role.LIBRARIAN));
        forward(PagesPaths.Dashboard.Librarians.LIST, request, response);
    }

}
