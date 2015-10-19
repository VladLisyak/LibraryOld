package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.publisher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Basic servlet for servlets of publishers management.
 */
public abstract class PublisherServlet extends DashboardServlet{

	private static final long serialVersionUID = -2003157467414694932L;

	protected Publisher getPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIntParam(request, Constants.Attributes.ID);
        if (id == null){
            redirectBack(request, response);
            return null;
        }
        
        Publisher publisher = getPublisherService().getById(id);
        if (publisher == null){
            redirectBack(request, response);
            return null;
        }
        return publisher;
    }

    protected Publisher getPublisherFromRequest(HttpServletRequest request, HttpServletResponse response, boolean isNew) throws ServletException, IOException {
        Publisher publisher = new Publisher();
        if (!isNew){
            Integer id = getIntParam(request, Constants.Attributes.ID);
            if (id == null){
                redirectBack(request, response);
                return null;
            }
            
            publisher.setId(id);
        }
        Interpretation title = getInterpretation(request, Constants.Attributes.TITLE);
        publisher.setTitle(title);
        return publisher;
    }

    protected void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.publisherNotFound");
        redirectToAction(Actions.Admin.Publishers.PUBLISHERS_PATH, request, response);
    }

    protected void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, Publisher publisher) throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute("publisher", publisher);
        forward(getForwardPage(), request, response);
    }

    protected abstract String getForwardPage();

}
