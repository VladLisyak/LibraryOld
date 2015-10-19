package ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.servlet.siteManagingServs.adminServs.DashboardServlet;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.siteNavigation.Actions;
import ua.nure.lisyak.SummaryTask4.validation.Validator;

/**
 * Basic servlets for {@link Author}s management servlet.
 */
public abstract class AuthorServlet extends DashboardServlet {

	private static final long serialVersionUID = 1202706309231207407L;
	protected Author getAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer authorId = getIntParam(request, Constants.Attributes.ID);
        if (authorId == null) {
            redirectBack(request, response);
            return null;
        }
        Author author = getAuthorService().getById(authorId);
        if (author == null) {
            redirectBack(request, response);
            return null;
        }
        return author;
    }

    protected void redirectBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setResult(request, false, "validator.authorNotFound");
        redirectToAction(Actions.Admin.Authors.AUTHORS_PATH, request, response);
    }

    protected void forwardBack(HttpServletRequest request, HttpServletResponse response, Validator validator, Author author)
            throws ServletException, IOException {
        request.setAttribute("messages", validator.getMessages());
        request.setAttribute("author", author);
        forward(getForwardPage(), request, response);
    }

    protected Author getAuthorFromRequest(HttpServletRequest requset, HttpServletResponse response, boolean isNew) throws ServletException, IOException {
        Author authorToGet = new Author();
        if (!isNew) {
            Integer authorId = getIntParam(requset, Constants.Attributes.ID);
            if (authorId == null) {
                redirectBack(requset, response);
                return null;
            }
            authorToGet.setId(authorId);
        }
        Interpretation name = getInterpretation(requset, Constants.Attributes.NAME);
        Interpretation description = getInterpretation(requset, Constants.Attributes.DESCRIPTION);
        authorToGet.setName(name);
        authorToGet.setDescription(description);
        return authorToGet;
    }

    protected abstract String getForwardPage();
}
