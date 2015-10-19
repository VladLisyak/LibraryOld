package ua.nure.lisyak.SummaryTask4.servlet.mainServs.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.servlet.mainServs.BaseMain;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.PagesPaths;


/**
 * Servlet for viewing particular {@link Author}.
 */
@WebServlet(Actions.Common.Authors.ITEM)
public class Item extends BaseMain {

	private static final long serialVersionUID = -5259683213637468928L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = getAuthor(request, response);
        
        if (author == null) {
            return;
        }
        
        request.setAttribute(Constants.Attributes.AUTHOR, author);
        request.setAttribute(Constants.Attributes.BOOKS, getBookService().getAll(author));
        forward(PagesPaths.Main.Authors.ITEM, request, response);
    }

	/**
	 * Gets {@link Author} from request
	 * @return {@link Author} retrieved from request.
	 * @throws ServletException
	 * @throws IOException
	 */
    private Author getAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIntParam(request, Constants.Attributes.ID);
        if (id == null) {
            redirectBack(request, response);
            return null;
        }
        Author author = getAuthorService().getById(id);
        
        if (author == null) {
            redirectBack(request, response);
            return null;
        }
        return author;
    }

    private void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirectToAction(Actions.Common.HOME, request, response);
    }

}
