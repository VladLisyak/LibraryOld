package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.publisher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.PublisherValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;


/**
 * Servlet for {@link Publisher} editing.
 */
@WebServlet(Actions.Admin.Publishers.EDIT)
public class Edit extends PublisherServlet {

	private static final long serialVersionUID = -1040001089476553158L;


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Publisher publisher = getPublisher(request, response);
        if (publisher == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.PUBLISHER, publisher);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Publisher publisher = getPublisherFromRequest(request, response, false);
        if (publisher == null) {
            return;
        }

        Publisher currPublisher = getPublisherService().getById(publisher.getId());
        if (currPublisher == null) {
            redirectBack(request, response);
            return;
        }

        Validator validator = new PublisherValidator(publisher, getInterp(), getLocale(request), getLocales());

        if (validator.hasErrors()){
            forwardBack(request, response, validator, publisher);
            return;
        }

        updatePublisher(currPublisher, publisher);
        setResult(request, true, "res.publisherEdited");
        redirectToAction(Actions.Admin.Publishers.PUBLISHERS_PATH, request, response);

    }

    @Override
    protected String getForwardPage(){
        return PagesPaths.Dashboard.Publishers.EDIT;
    }


    private void updatePublisher(Publisher currPublisher, Publisher updPublisher) {
        Interpretation title = currPublisher.getTitle();
        title.clone(updPublisher.getTitle());
        getInterpretationService().update(title);
    }

}
