package ua.nure.lisyak.SummaryTask4.service.servicesImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.SubscriptionDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.UserDao;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.SubscriptionDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.UserDAOImpl;
import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.Subscription;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.BookUser;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.service.UserService;
import ua.nure.lisyak.SummaryTask4.util.Constants;


/**
 * Provides implementation of {@link UserService} interface for manipulating {@link User} entities.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDAO = new UserDAOImpl();

    private SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();

    @Override
    public User add(User user) {
        return userDAO.add(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void confirm(User user) {
        user.setRole(Role.USER);
        userDAO.update(user);
        Subscription subs = new Subscription();
        subs.setUserId(user.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, Constants.Settings.SUBSCRIPTION_DURATION);
        Date date = calendar.getTime();
        subs.setExpirationDate(new java.sql.Date(date.getTime()));
        
        subscriptionDAO.add(subs);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user.getId());
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public Reader getReaderById(User usr) {
        if (usr == null) {
            return null;
        }
        return getReaderById(usr.getId());
    }

    @Override
    public Reader getReaderById(int id) {
        return userDAO.getReaderById(id);
    }

    @Override
    public boolean readerExists(int subsId) {
        return subscriptionDAO.exists(subsId);
    }

    @Override
    public User getByLogin(String login) {
        return getByLogin(login ,0);
    }

    @Override
    public User getByLogin(String login, int excId) {
        return userDAO.getByLogin(login, excId);
    }

    @Override
    public User getByEmail(String email, int excId) {
        return userDAO.getByEmail(email, excId);
    }

    @Override
    public List<User> getByRole(Role role) {
        return userDAO.getByRole(role);
    }

    @Override
    public int countByRole(Role role) {
        return userDAO.countByRole(role);
    }

    @Override
    public List<Reader> getAllReaders() {
        return userDAO.getAllReaders();
    }

    @Override
    public void updateSubs() {
        userDAO.updateSubscriptions();
    }

    @Override
    public void updateSubs(Reader reader) {
        reader.setRole(Role.USER);
        userDAO.update(reader);
        subscriptionDAO.update(reader.getSubscription());
    }

    @Override
    public List<BookUser> getBooks() {
        return userDAO.getOrders();
    }

}