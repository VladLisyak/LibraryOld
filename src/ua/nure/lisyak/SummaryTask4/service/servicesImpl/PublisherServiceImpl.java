package ua.nure.lisyak.SummaryTask4.service.servicesImpl;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.PublisherDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.PublisherDAOImpl;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.service.PublisherService;

/**
 * Provides implementation of {@link PublisherService} interface 
 * for manipulating with {@link Publisher} entities.
 */
public class PublisherServiceImpl implements PublisherService {

    private PublisherDAO publisherDAO = new PublisherDAOImpl();

    @Override
    public Publisher add(Publisher publisher) {
        return publisherDAO.add(publisher);
    }

    @Override
    public Publisher getById(int id) {
        return publisherDAO.getById(id);
    }

    @Override
    public List<Publisher> getAll() {
        return publisherDAO.getAll();
    }

    @Override
    public void update(Publisher publisher) {
        publisherDAO.update(publisher);
    }

    @Override
    public void delete(Publisher publisher) {
        publisherDAO.delete(publisher.getId());
    }

    @Override
    public int countAll() {
        return publisherDAO.countAll();
    }
}
