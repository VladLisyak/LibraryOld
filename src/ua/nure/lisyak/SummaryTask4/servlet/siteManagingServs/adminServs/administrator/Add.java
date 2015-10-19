package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.administrator;

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
 * Servlet for adding a new {@link Role#LIBRARIAN}.
 */
@WebServlet(Actions.Admin.Admins.ADD)
public class Add extends AdministratorServlet {

	private static final long serialVersionUID = -3077920653689596073L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User adminToAdd = new User();
        adminToAdd.setFirstName(request.getParameter("firstName"));
        adminToAdd.setLastName(request.getParameter("lastName"));
        adminToAdd.setEmail(request.getParameter("email"));
        adminToAdd.setLogin(request.getParameter("login"));
        adminToAdd.setPassword(request.getParameter("password"));
        String locale = getLocale(request);
        Validator userValidator = new UserValidator(adminToAdd, getInterp(), locale);
        if (userValidator.hasErrors()) {
            forwardBack(request, response, userValidator, adminToAdd);
            return;
        }
        checkUniqueness(adminToAdd, userValidator);
        if (userValidator.hasErrors()) {
            forwardBack(request, response, userValidator, adminToAdd);
            return;
        }
        adminToAdd.setRole(Role.ADMINISTRATOR);
        adminToAdd.setPassword(adminToAdd.getPassword());
        getUserService().add(adminToAdd);
        setResult(request, true, "res.administratorAdded");
        redirectToAction(Actions.Admin.Admins.ADMINISTRATORS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Admins.ADD;
    }
}
