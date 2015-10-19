package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;



/**
 * Servlet for viewing an {@link Admin} main module.
 */
@WebServlet(Actions.Admin.DASHBOARD_INDEX)
public class Home extends DashboardServlet {
	private static final long serialVersionUID = 2681175406438313075L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("booksCount", getBookService().countAll());
        request.setAttribute("readersCount", getUserService().countByRole(Role.USER));
        request.setAttribute("publishersCount", getPublisherService().countAll());
        request.setAttribute("authorsCount", getAuthorService().countAll());
        forward(PagesPaths.Dashboard.INDEX, request, response);
    }
	
}
