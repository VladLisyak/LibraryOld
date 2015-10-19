package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.user;

import javax.servlet.annotation.WebServlet;

import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;


/**
 * Servlet for extending subscriptions of users,
 * whose subscriptions are actual for the moment.
 */
@WebServlet(Actions.Admin.Users.EXTEND_ACTUAL)
public class ExtendActual extends Extend {

	private static final long serialVersionUID = 6830801582353884824L;

	@Override
    protected String getRedirectAction(){
        return Actions.Admin.Users.USERS_PATH;
    }

    @Override
    protected String getSubmitAction(){
        return Actions.Admin.Users.EXTEND_ACTUAL;
    }
}
