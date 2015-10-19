package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.publisher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for {@link Publisher} deleting.
 */
@WebServlet(Actions.Admin.Publishers.DELETE)
public class Delete extends PublisherServlet {

	private static final long serialVersionUID = 1856935515559706547L;

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
        Publisher pub = getPublisher(request, response);
        if (pub == null) {
            return;
        }
        int num = getOrderService().countByPub(pub);
        if (num != 0) {
            setResult(request, false, "res.cannotDeletePublisher");
            redirectToAction(getForwardPage(), request, response);
            return;
        }
        List<String> booksPhotos = getBookService().getImgsByPub(pub);
        getFileService().removeFiles(booksPhotos, Constants.Settings.UPLOAD_BOOKS_DIRECTORY);
        getPublisherService().delete(pub);
        setResult(request, true, "res.publisherDeleted");
        redirectToAction(Actions.Admin.Publishers.PUBLISHERS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Publishers.DELETE;
    }
}
