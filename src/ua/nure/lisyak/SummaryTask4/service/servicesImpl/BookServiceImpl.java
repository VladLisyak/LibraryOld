package ua.nure.lisyak.SummaryTask4.service.servicesImpl;

import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import ua.nure.lisyak.SummaryTask4.db.dao.BookDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.BookDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.sort.SortKey;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.service.BookService;
/**
 * Provides implementation of {@link BookService} interface for manipulating with {@link Book} entities.
 */
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public Book add(Book book) {
        return bookDAO.add(book);
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void updateWithImg(Book book) {
        bookDAO.updateWithImg(book);
    }

    @Override
    public void delete(Book book) {
        bookDAO.delete(book.getId());
    }

    @Override
    public boolean exists(int id) {
        return bookDAO.exists(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public List<Book> getAll(List<Integer> ids) {
        return bookDAO.getAll(ids);
    }

    @Override
    public List<Book> getAll(Author author) {
        if (author == null) {
            return null;
        }
        return bookDAO.getAll(author.getId());
    }

    @Override
    public int countAll() {
        return bookDAO.countAll();
    }

    @Override
    public List<String> getImgsByAuthor(Author author) {
        return bookDAO.getImgsByAuthor(author.getId());
    }

    @Override
    public List<String> getImgsByPub(Publisher publisher) {
        return bookDAO.getImgsByPublisher(publisher.getId());
    }

    @Override
    public Book getById(int id) {
        return bookDAO.getById(id);
    }

    @Override
    public Pair<List<Book>, Integer> getAll(int currPage, int itemsPerPage, String search, String orderBy, String[] locales, String locale) {
    	return bookDAO.search((currPage - 1) * itemsPerPage, itemsPerPage, search, orderBy, locales, locale);
    }

    @Override
    public Map<String, SortKey> getSortKeys() {
        return bookDAO.getSortKeys();
    }

}
