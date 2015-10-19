package ua.nure.lisyak.SummaryTask4.servlet.mainServs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
/**
 * Servlet for viewing profile and canceling not confirmed orders
 */
@WebServlet(Actions.Common.PROFILE)
public class Profile extends BaseMain {
    private static final Logger LOGGER = Logger.getLogger(Profile.class);
  
	private static final long serialVersionUID = -6847730068494145323L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getCurrentUser(request);
      
        if (user == null) {
            redirectToAction(Actions.Common.HOME, request, response);
            return;
        }
        Reader reader = getUserService().getReaderById(user);
        request.setAttribute("userOrd", Role.USER);
        request.setAttribute("reader", reader);
        request.setAttribute("orders", getOrderService().getOrdered(reader));
        request.setAttribute("checkOuts", getOrderService().getCheckedOut(reader));
        forward(PagesPaths.Main.PROFILE, request, response);
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = getIntParam(request, "orderID", -1);
		LOGGER.debug(id);
		if (id >= 0){
		getOrderService().delete(id);}
		redirectToAction(Actions.Common.PROFILE, request, response);
	}

}