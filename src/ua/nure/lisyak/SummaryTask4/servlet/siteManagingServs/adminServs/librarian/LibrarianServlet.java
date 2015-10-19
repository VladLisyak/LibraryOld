package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Basic servlet for managing {@link Librarian}s.
 */
public abstract class LibrarianServlet extends DashboardServlet {

	private static final long serialVersionUID = 5871717136192425909L;

	protected User getLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer librarianId = getIntParam(request, Constants.Attributes.ID);
        if (librarianId == null) {
            redirectBack(request, response);
            return null;
        }
        User user = getUserService().getById(librarianId);
        if (user == null || user.getRole() != Role.LIBRARIAN) {
            redirectBack(request, response);
            return null;
        }
        
        return user;
    }

    protected void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.librarianNotFound");
        redirectToAction(Actions.Admin.Librarians.LIBRARIANS_PATH, request, response);
    }

    protected void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, User librarian)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute(Constants.Attributes.LIBRARIAN, librarian);
        forward(getForwardPage(), request, response);
    }

    protected abstract String getForwardPage();

}
