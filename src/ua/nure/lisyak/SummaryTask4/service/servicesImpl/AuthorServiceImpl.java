package ua.nure.lisyak.SummaryTask4.service.servicesImpl;



import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.AuthorDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.AuthorDAOImpl;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.service.AuthorService;

/**
 * Provides implementation of {@link AuthorService} interface for manipulating with {@link Author} entities.
 */
public class AuthorServiceImpl implements AuthorService {

    private AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public Author add(Author author) {
        return authorDAO.add(author);
    }

    @Override
    public Author getById(int id) {
        return authorDAO.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDAO.getAll();
    }

    @Override
    public List<Author> getAll(List<Integer> idS) {
        return authorDAO.getAll(idS);
    }

    @Override
    public void update(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void delete(Author author) {
        authorDAO.delete(author.getId());
    }

    @Override
    public int countAll() {
        return authorDAO.countAll();
    }


}
