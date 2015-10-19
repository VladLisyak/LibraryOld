package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.user;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.Subscription;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Constants.Attributes;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for extending overdue {@link Subscription}s of {@link Readers} .
 */
@WebServlet(Actions.Admin.Users.EXTEND_OVERDUE)
public class Extend extends DashboardServlet {
	private static final long serialVersionUID = 4898295119309119855L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usr = getReader(request, response);
        if (usr == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.USER, usr);
        request.setAttribute("submitUrl", getSubmitAction());
        request.setAttribute("backUrl", getRedirectAction());
        forward(PagesPaths.Dashboard.Users.EXTEND, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = getReader(request, response);
        if (reader == null) {
            return;
        }
        changeExpirationDate(reader, 12);
        
        getUserService().updateSubs(reader);
        setResult(request, true, "res.subscription.extended");
        redirectToAction(getRedirectAction(), request, response);
    }

    protected String getSubmitAction() {
        return Actions.Admin.Users.EXTEND_OVERDUE;
    }

    private Reader getReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String unparsedId = request.getParameter(Attributes.ID);
        if (unparsedId == null) {
            redirectBack(request, response);
            return null;
        }
        int parsedId;
        try {
            parsedId = Integer.parseInt(unparsedId);
        } catch (NumberFormatException e) {
            redirectBack(request, response);
            return null;
        }
        Reader user = getUserService().getReaderById(parsedId);
        if (user == null || (user.getRole() != Role.OVERDUE && user.getRole() != Role.USER)) {
            redirectBack(request, response);
            return null;
        }
        return user;
    }

    protected String getRedirectAction() {
        return Actions.Admin.Users.OVERDUE;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.userNotFound");
        redirectToAction(getRedirectAction(), request, response);
    }

    /**
     * Changes expiration date of specified readers subscription
     * @param {@link Reader} reader whose subscription needs to be changed.
     * @param months number of months to extend at.
     */
    private void changeExpirationDate(Reader reader, int months) {
        Subscription subs = reader.getSubscription();
        Calendar calendar = Calendar.getInstance();
        if (reader.getRole() == Role.USER) {
            calendar.setTime(subs.getExpirationDate());
        } else {
            calendar.setTime(new Date());
        }
        calendar.add(Calendar.MONTH, months);
        subs.setExpirationDate(new java.sql.Date(calendar.getTime().getTime()));
        reader.setSubscription(subs);
    }

}
