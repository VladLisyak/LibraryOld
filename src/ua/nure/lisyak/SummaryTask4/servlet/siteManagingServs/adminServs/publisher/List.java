package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.publisher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet for viewing a list of {@link Publisher}s.
 */
@WebServlet(Actions.Admin.Publishers.PUBLISHERS_PATH)
public class List extends DashboardServlet {
	private static final long serialVersionUID = 400507818444447692L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getResult(request);
        request.setAttribute(Constants.Attributes.PUBLISHERS, getPublisherService().getAll());
        forward(PagesPaths.Dashboard.Publishers.LIST, request, response);
    }

}
