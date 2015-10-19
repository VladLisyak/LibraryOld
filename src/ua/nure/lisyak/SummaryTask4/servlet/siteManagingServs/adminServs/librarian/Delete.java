package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet for {@link Librarian} deleting.
 */
@WebServlet(Actions.Admin.Librarians.DELETE)
public class Delete extends LibrarianServlet {

	private static final long serialVersionUID = -4432709369048044026L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User librarian = getLibrarian(request, response);
        if (librarian == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.LIBRARIAN, librarian);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User librarian = getLibrarian(request, response);
        if (librarian == null) {
            return;
        }
        getUserService().delete(librarian);
        setResult(request, true, "res.librarianDeleted");
        redirectToAction(Actions.Admin.Librarians.LIBRARIANS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Librarians.DELETE;
    }

}
