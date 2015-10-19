package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.UserValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;
import ua.nure.lisyak.SummaryTask4.util.Constants.*;

/**
 * Servlet for {@link Administrator} editing.
 */
@WebServlet(Actions.Admin.Admins.EDIT)
public class Edit extends AdministratorServlet {

	private static final long serialVersionUID = 2487730464344877773L;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User adminToEdit = getAdmin(request, response);
        if (adminToEdit == null) {
            return;
        }
        request.setAttribute(Attributes.ADMINISTRATOR, adminToEdit);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer adminId = getIntParam(request, Attributes.ID);
        if (adminId == null) {
            redirectBack(request, response);
            return;
        }
        User oldAdmin = getUserService().getById(adminId);
        if (oldAdmin == null) {
            redirectBack(request, response);
            return;
        }
        User updAdmin = new User();
        updAdmin.setId(oldAdmin.getId());
        updAdmin.setFirstName(getStringParam(request, "firstName"));
        updAdmin.setLastName(getStringParam(request, "lastName"));
        updAdmin.setEmail(request.getParameter("email"));
        updAdmin.setLogin(getStringParam(request, "login"));
        boolean changePassword = Boolean.parseBoolean(request.getParameter("changePassword"));
        updAdmin.setPassword(changePassword ? getStringParam(request, "password") : oldAdmin.getPassword());
        String locale = getLocale(request);
        Validator validator = new UserValidator(updAdmin, getInterp(), locale);
        if (validator.hasErrors()){
            forwardBack(request, response, validator, updAdmin);
            return;
        }
        checkUniqueness(updAdmin, validator);
        if (validator.hasErrors()) {
            forwardBack(request, response, validator, updAdmin);
            return;
        }
        updAdmin.setRole(oldAdmin.getRole());
        if (changePassword) {
            updAdmin.setPassword(updAdmin.getPassword());
        }
        getUserService().update(updAdmin);
        setResult(request, true, "res.administratorEdited");
        redirectToAction(Actions.Admin.Admins.ADMINISTRATORS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Admins.EDIT;
    }

}
