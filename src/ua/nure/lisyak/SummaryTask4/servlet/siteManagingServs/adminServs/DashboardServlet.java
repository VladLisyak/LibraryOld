package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.lisyak.SummaryTask4.service.PublisherService;
import ua.nure.lisyak.SummaryTask4.service.InterpretationService;
import ua.nure.lisyak.SummaryTask4.servlet.BaseServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.ProcessResult;

/**
 * Initial servlet for all servlets of dashboard module.
 */
public abstract class DashboardServlet extends BaseServlet {

	private static final long serialVersionUID = -8054059373850744348L;
	
	private PublisherService pubService;
	private InterpretationService interpService;
	
	@Override
    public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
        pubService = (PublisherService) context.getAttribute(Constants.Service.PUBLISHER_SERVICE);
        interpService = (InterpretationService) context.getAttribute(Constants.Service.ITERPRETATION_SERVICE);
    }

	/**
	 * Gets from session result of some state-changing operation.
	 * @param request
	 */
	protected void getResult(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ProcessResult result = (ProcessResult) session.getAttribute(Constants.Attributes.PROCESS_RESULT_SESSION);
        request.setAttribute(Constants.Attributes.PROCESS_RESULT, result);
        session.removeAttribute(Constants.Attributes.PROCESS_RESULT_SESSION);
    }

	/**
	 * Sets to session result of some state-changing operation.
	 * @param request
	 */
    protected void setResult(HttpServletRequest request, boolean isSucceeded, String message) {
        HttpSession session = request.getSession();
        ProcessResult procResult = new ProcessResult();
        procResult.setSucceeded(isSucceeded);
        procResult.setMessage(getInterp().translate(message, getLocale(request)));
        session.setAttribute(Constants.Attributes.PROCESS_RESULT_SESSION, procResult);
    }
    
    protected InterpretationService getInterpretationService() {
        return interpService;
    }
    
    protected PublisherService getPublisherService() {
        return pubService;
    }

}
