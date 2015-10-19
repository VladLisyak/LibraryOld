package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.administrator;

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
 * Servlet for {@link Administrator} deleting.
 */
@WebServlet(Actions.Admin.Admins.DELETE)
public class Delete extends AdministratorServlet {

	private static final long serialVersionUID = -1963905525846063257L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User adminToDelete = getAdmin(request, response);
        if (adminToDelete == null) {
            return;
        }
        if (adminToDelete.getId() == getCurrentUser(request).getId()) {
            setResult(request, false, "validator.cannotDeleteYourself");
            redirectToAction(Actions.Admin.Admins.ADMINISTRATORS_PATH, request, response);
            return;
        }
        request.setAttribute(Constants.Attributes.ADMINISTRATOR, adminToDelete);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User adminToDelete = getAdmin(request, response);
        if (adminToDelete == null) {
            return;
        }
        if (adminToDelete.getId() == getCurrentUser(request).getId()) {
            setResult(request, false, "validator.cannotDeleteYourself");
            redirectToAction(Actions.Admin.Admins.ADMINISTRATORS_PATH, request, response);
            return;
        }
        getUserService().delete(adminToDelete);
        setResult(request, true, "res.administratorDeleted");
        redirectToAction(Actions.Admin.Admins.ADMINISTRATORS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Admins.DELETE;
    }

}
