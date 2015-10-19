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
import ua.nure.lisyak.SummaryTask4.validation.UserValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Servlet for editing librarians.
 */
@WebServlet(Actions.Admin.Librarians.EDIT)
public class Edit extends LibrarianServlet {

	private static final long serialVersionUID = 3418660509170463776L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usr = getLibrarian(request, response);
        if (usr == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.LIBRARIAN, usr);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User oldLibrarian = getLibrarian(request, response);
        if (oldLibrarian == null) {
            return;
        }
        User updLibrarian = new User();
        updLibrarian.setId(oldLibrarian.getId());
        updLibrarian.setFirstName(getStringParam(request, "firstName"));
        updLibrarian.setLastName(getStringParam(request, "lastName"));
        updLibrarian.setEmail(getStringParam(request, "email"));
        updLibrarian.setLogin(getStringParam(request, "login"));
        boolean changePassword = Boolean.parseBoolean(request.getParameter("changePassword"));
        updLibrarian.setPassword(changePassword ? getStringParam(request, "password") : oldLibrarian.getPassword());
        String locale = getLocale(request);
        Validator userValidator = new UserValidator(updLibrarian, getInterp(), locale);
        if (userValidator.hasErrors()) {
            forwardBack(request, response, userValidator, updLibrarian);
            return;
        }
        checkUniqueness(updLibrarian, userValidator);
        if (userValidator.hasErrors()) {
            forwardBack(request, response, userValidator, updLibrarian);
            return;
        }
        updLibrarian.setRole(oldLibrarian.getRole());
        if (changePassword) {
            updLibrarian.setPassword(updLibrarian.getPassword());
        }
        getUserService().update(updLibrarian);
        setResult(request, true, "res.librarianEdited");
        redirectToAction(Actions.Admin.Librarians.LIBRARIANS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Librarians.EDIT;
    }

}
