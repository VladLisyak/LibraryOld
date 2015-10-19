package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.UserValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Responsible for adding a new librarian.
 */
@WebServlet(Actions.Admin.Librarians.ADD)
public class Add extends LibrarianServlet {

	private static final long serialVersionUID = -6068839018939791371L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User librarian = new User();
        librarian.setFirstName(request.getParameter("firstName"));
        librarian.setLastName(request.getParameter("lastName"));
        librarian.setEmail(request.getParameter("email"));
        librarian.setLogin(request.getParameter("login"));
        librarian.setPassword(request.getParameter("password"));
        String locale = getLocale(request);
        Validator validator = new UserValidator(librarian, getInterp(), locale);
        if (validator.hasErrors()){
            forwardBack(request, response, validator, librarian);
            return;
        }
        checkUniqueness(librarian, validator);
        if (validator.hasErrors()) {
            forwardBack(request, response, validator, librarian);
            return;
        }
        librarian.setRole(Role.LIBRARIAN);
        getUserService().add(librarian);
        setResult(request, true, "res.librarianAdded");
        redirectToAction(Actions.Admin.Librarians.LIBRARIANS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Librarians.ADD;
    }
}
