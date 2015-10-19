package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.dao.UserDao;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.BookUser;
import ua.nure.lisyak.SummaryTask4.entity.Role;
import ua.nure.lisyak.SummaryTask4.exception.DAException;

/**
 * DAO for {@link User}s
 */
public class UserDAOImpl extends CRUDDAOImpl<User> implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE BINARY login = ? AND id != ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE BINARY email = ? AND id != ?";
    private static final String SELECT_USER_BY_ROLE = "SELECT * FROM users WHERE role = ?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String SELECT_ALL_BY_IDS = "SELECT * FROM users WHERE id IN {in}";
    private static final String SELECT_ALL_BOOKS_BY_TYPE = "SELECT * FROM userBooks";
    private static final String COUNT_USERS_BY_ROLE = "SELECT COUNT(*) FROM users WHERE role = ?";
    private static final String INSERT_USER = "INSERT INTO users (firstName, lastName, email, login, password, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET firstName=?, lastName=?, email=?, login=?, password=?, role=? WHERE id = ?";
    private static final String UPDATE_USERS_SUBSCRIPTION = "UPDATE users SET role=? WHERE id IN (SELECT userId FROM subscriptions WHERE expirationDate < ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_ALL_READERS_BY_ID = "SELECT * FROM readersView WHERE id = ?";
    private static final String SELECT_ALL_READERS = "SELECT * FROM readersView WHERE role = ? ORDER BY id";
    
    private Parser<User> parser = new Parser<>(User.class);
    private Parser<Reader> readerParser = new Parser<>(Reader.class);
    private Parser<BookUser> userBookParser = new Parser<>(BookUser.class);

    @Override
    public User add(User user) {
        return add(user, INSERT_USER);
    }

    @Override
    public void update(User user) {
        update(user, UPDATE_USER);
    }

    @Override
    public void delete(int id) {
        deleteById(DELETE_USER, id);
    }

    @Override
    public User getById(int id) {
        return getById(id, SELECT_USER_BY_ID, parser);
    }

    /**
     * Gets {@link Reader} by id.
     *
     * @param id {@link Reader}s id
     * @return found {@link Reader} object 
     * 
     * @see Reader
     */
    public Reader getReaderById(int id) {
        return getById(id, SELECT_ALL_READERS_BY_ID, readerParser);
    }

    @Override
    public List<User> getAll() {
        return getAll(SELECT_ALL, parser);
    }

    @Override
    public List<User> getAll(List<Integer> ids) {
        return getAll(ids, SELECT_ALL_BY_IDS, parser);
    }

    /**
     * Gets {@link User} by login except of users with specified id.
     *
     * @param login login of User that should be found
     * @param excludeId Id that must be ignored
     * @return {@link User} found
     * 
     * @see User
     */
    public User getByLogin(String login, int excludeId) {
        String sql = SELECT_USER_BY_LOGIN;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, login);
            pst.setInt(2, excludeId);
            List<User> usersList = executeQuery(pst, parser);
            return usersList.isEmpty() ? null : usersList.get(0);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Gets user by email except of users with specified id..
     *
     * @param email  email of the user
     * @param excludeId id of the user that must be ignored
     * @return user found
     * 
     * @see User
     */
    public User getByEmail(String email, int excludeId) {
        String sql = SELECT_USER_BY_EMAIL;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setInt(2, excludeId);
            List<User> usersList = executeQuery(pst, parser);
            return usersList.isEmpty() ? null : usersList.get(0);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Gets all users of specified role.
     *
     * @param role role of {@link User} to be found
     * @return list of found {@link Users}
     */
    public List<User> getByRole(Role role) {
        String sql = SELECT_USER_BY_ROLE;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, role.ordinal());
            return executeQuery(pst, parser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Gets all {@link Reader}s.
     *
     * @return list of found {@link Reader}s
     */
    public List<Reader> getAllReaders() {
        String sql = SELECT_ALL_READERS;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, Role.USER.ordinal());
            return executeQuery(pst, readerParser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Counts the number of ${@link User}s of specified {@link Role}.
     *
     * @param role {@link Role} of users that should be counted
     * @return the number of {@link User}s of specified role
     */
    public int countByRole(Role role) {
        String sql = COUNT_USERS_BY_ROLE;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, role.ordinal());
            return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Updates subscriptions of all {@link Reader}s.
     */
    public void updateSubscriptions() {
        String sql = UPDATE_USERS_SUBSCRIPTION;
        Date date = new Date(new java.util.Date().getTime());
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, Role.OVERDUE.ordinal());
            pst.setDate(2, date);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Get {@link Book}s by order type.
     *
     * @return list of all overdued books
     */
    public List<BookUser> getOrders() {
        String sql = SELECT_ALL_BOOKS_BY_TYPE;
        return getAll(sql, userBookParser);
    }

    @Override
    protected void prepareForIns(User user, PreparedStatement pst) throws SQLException {
        pst.setString(1, user.getFirstName());
        pst.setString(2, user.getLastName());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getLogin());
        pst.setString(5, user.getPassword());
        pst.setInt(6, user.getRole().ordinal());
    }

    @Override
    protected void prepareForUpd(User user, PreparedStatement pst) throws SQLException {
        prepareForIns(user, pst);
        pst.setInt(7, user.getId());
    }
    
}
