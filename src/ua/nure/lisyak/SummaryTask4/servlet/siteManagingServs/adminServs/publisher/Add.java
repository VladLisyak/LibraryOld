package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.publisher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;
import ua.nure.lisyak.SummaryTask4.validation.PublisherValidator;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Servlet for adding a new {@link Publisher}.
 */
@WebServlet(Actions.Admin.Publishers.ADD)
public class Add extends PublisherServlet {

	private static final long serialVersionUID = -4472081507389726379L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Publisher publisher = getPublisherFromRequest(request, response, true);
        if (publisher == null) {
            return;
        }
        Validator pubValidator = new PublisherValidator(publisher, getInterp(), getLocale(request), getLocales());
        
        if (pubValidator.hasErrors()) {
            forwardBack(request, response, pubValidator, publisher);
            return;
        }
        savePublisher(publisher);
        
        setResult(request, true, "res.publisherAdded");
        redirectToAction(Actions.Admin.Publishers.PUBLISHERS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Publishers.ADD;
    }

    private Publisher savePublisher(Publisher pub) {
        Interpretation title = getInterpretationService().add(pub.getTitle());
        pub.setTitle(title);
        return getPublisherService().add(pub);
    }

}
