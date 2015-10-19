package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.administrator;

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
 * Basic servlet for administrators management servlets.
 */
public abstract class AdministratorServlet extends DashboardServlet {

	private static final long serialVersionUID = -986786098215510319L;

	protected User getAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer adminId = getIntParam(request, Constants.Attributes.ID);
        if (adminId == null) {
            redirectBack(request, response);
            return null;
        }
        User admin = getUserService().getById(adminId);
        if (admin == null || admin.getRole() != Role.ADMINISTRATOR) {
            redirectBack(request, response);
            return null;
        }
        return admin;
    }

    protected void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.administratorNotFound");
        redirectToAction(Actions.Admin.Admins.ADMINISTRATORS_PATH, request, response);
    }

    protected void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, User admin)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute(Constants.Attributes.ADMINISTRATOR, admin);
        forward(getForwardPage(), request, response);
    }

    protected abstract String getForwardPage();

}
