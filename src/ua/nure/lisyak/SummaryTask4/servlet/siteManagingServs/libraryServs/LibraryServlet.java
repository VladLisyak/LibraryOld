package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.libraryServs;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.lisyak.SummaryTask4.servlet.BaseServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.ProcessResult;

/**
 * Initial servlet for all servlets of library module.
 */
public abstract class LibraryServlet extends BaseServlet {

	private static final long serialVersionUID = 7151334565096600654L;

	protected void getResult(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProcessResult result = (ProcessResult) session.getAttribute(Constants.Attributes.PROCESS_RESULT_SESSION);
        request.setAttribute(Constants.Attributes.PROCESS_RESULT, result);
        session.removeAttribute(Constants.Attributes.PROCESS_RESULT_SESSION);
    }

    protected void setResult(HttpServletRequest request, boolean succeeded, String message) {
        HttpSession session = request.getSession();
        ProcessResult procResult = new ProcessResult();
        procResult.setSucceeded(succeeded);
        procResult.setMessage(getInterp().translate(message, getLocale(request)));
        session.setAttribute(Constants.Attributes.PROCESS_RESULT_SESSION, procResult);
    }

}
