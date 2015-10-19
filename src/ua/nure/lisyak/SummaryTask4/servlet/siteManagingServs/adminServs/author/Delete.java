package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.author;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;

/**
 * Servlet for {@link Author} deleting.
 */
@WebServlet(Actions.Admin.Authors.DELETE)
public class Delete extends AuthorServlet {

	private static final long serialVersionUID = 3711992462832095965L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author authorToDelete = getAuthor(request, response);
        if (authorToDelete == null) {
            return;
        }
        request.setAttribute(Constants.Attributes.AUTHOR, authorToDelete);
        forward(getForwardPage(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author authorToDelete = getAuthor(request, response);
        if (authorToDelete == null) {
            return;
        }
        int count = getOrderService().countByAuthor(authorToDelete);
        if (count != 0){
            setResult(request, false, "res.cannotDeleteAuthor");
            redirectToAction(Actions.Admin.Authors.DELETE, request, response);
            return;
        }

        List<String> booksPhotos = getBookService().getImgsByAuthor(authorToDelete);
        getFileService().removeFiles(booksPhotos, Constants.Settings.UPLOAD_AUTHORS_DIR);
        getFileService().removeFile(authorToDelete.getImage(), Constants.Settings.UPLOAD_AUTHORS_DIR);
        getAuthorService().delete(authorToDelete);
        setResult(request, true, "res.authorDeleted");
        redirectToAction(Actions.Admin.Authors.AUTHORS_PATH, request, response);
    }

    @Override
    protected String getForwardPage() {
        return PagesPaths.Dashboard.Authors.DELETE;
    }

}
