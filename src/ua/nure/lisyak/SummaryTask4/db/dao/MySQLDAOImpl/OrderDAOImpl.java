package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.dao.OrderDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.OrderDetails;
import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.exception.DAException;
import ua.nure.lisyak.SummaryTask4.util.Constants;


/**
 * DAO for {@link Order}s.
 */
public class OrderDAOImpl extends CRUDDAOImpl<Order> implements OrderDAO{

    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);
    
    private static final String COUNT_BY_AUTHOR = "SELECT COUNT(*) FROM orders LEFT JOIN books_authors ON orders.bookId = books_authors.bookId WHERE authorId = ? AND type=0";
    private static final String COUNT_BY_PUBLISHER = "SELECT COUNT(*) FROM orders LEFT JOIN books ON orders.bookId = books.id WHERE books.publisherId = ? AND type=0";
	private static final String COUNT_BY_BOOK = "SELECT COUNT(*) FROM orders WHERE bookId = ? AND type=0";
	private static final String COUNT_BY_TYPE = "SELECT COUNT(*) FROM orders WHERE type=?";
	private static final String SELECT_ALL = "SELECT * FROM orders";
	private static final String SELECT_ALL_BY_IDS = "SELECT * FROM orders WHERE id IN {in}";
    private static final String SELECT_ALL_BY_TYPE = "SELECT * FROM orders WHERE type=?";
    private static final String SELECT_BY_ID = "SELECT * FROM orders WHERE id=?";
    private static final String SELECT_BY_SUBSCRIPTION_ID = "SELECT * FROM ordersView WHERE subscriptionId=? AND type=?";
    private static final String INSERT = "INSERT INTO orders(type, subscriptionId, bookId, dueDate) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE orders SET type=?, dueDate=? WHERE id=?";
	private static final String UPDATE_OVERDUE = "UPDATE orders SET fee = fee + ? WHERE dueDate < ? AND type=?";
	private static final String UPDATE_OVERDUE_IN_READING_ROOMS = "UPDATE orders SET fee = fee + ?, type=? WHERE type=?";
	private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=?";
	private static final String DELETE_ORDERED = "DELETE FROM orders WHERE dueDate < ? AND type=?";
			
    private Parser<Order> parser = new Parser<>(Order.class);
    private Parser<OrderDetails> detailedOrderExtractor = new Parser<>(OrderDetails.class);


    @Override
    public Order add(Order order) {
    	return add(order, INSERT);
    }

    public List<Order> addAll(List<Order> orders) {
        return addAll(orders, INSERT);
    }

    
    @Override
    public void update(Order order) {
        update(order, UPDATE);
    }

    @Override
    public void delete(int id) {
        deleteById(DELETE_BY_ID, id);
    }

    @Override
    public Order getById(int id) {
        return getById(id, SELECT_BY_ID, parser);
    }

    public List<OrderDetails> getAll(int subsId, OrderType type) {
        String sql = SELECT_BY_SUBSCRIPTION_ID;
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, subsId);
            pst.setInt(2, type.ordinal());
            return executeQuery(pst, detailedOrderExtractor);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    @Override
    public List<Order> getAll() {
        return getAll(SELECT_ALL, parser);
    }

    
    public List<Order> getAll(List<Integer> idS) {
        return getAll(idS, SELECT_ALL_BY_IDS, parser);
    }

    public List<Order> getAll(OrderType type) {
        String sql = SELECT_ALL_BY_TYPE;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, type.ordinal());
            return executeQuery(pst, parser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    
    public int countByAuthor(int authId) {
        String sql = COUNT_BY_AUTHOR;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, authId);
            return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public int countByPublisher(int pubId) {
        String sql = COUNT_BY_PUBLISHER;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, pubId);
            return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    
    public int countByBook(int id) {
        String sql = COUNT_BY_BOOK;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public int countByType(OrderType type) {
        String sql = COUNT_BY_TYPE;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, type.ordinal());
            return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public void updateOrders() {
    	Date date = new Date(new java.util.Date().getTime());
    	
    	float fee = Constants.Settings.FEE;
        String sql = UPDATE_OVERDUE;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
           pst.setFloat(1, fee);
            pst.setDate(2, date);
            pst.setInt(3, OrderType.CHECKED_OUT.ordinal());
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public void updOrdersInReadingRooms() {
        float fee = Constants.Settings.FEE;
        String sql = UPDATE_OVERDUE_IN_READING_ROOMS;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setFloat(1, fee);
            pst.setInt(2, OrderType.ORDERED.ordinal());
            pst.setInt(3, OrderType.READING_ROOM.ordinal());
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public void deleteOverdueOrders() {
        Date date = new Date(new java.util.Date().getTime());
        String sql = DELETE_ORDERED;
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDate(1, date);
            pst.setInt(2, OrderType.ORDERED.ordinal());
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    @Override
    protected void prepareForIns(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getType().ordinal());
        ps.setInt(2, order.getSubscriptionId());
        ps.setInt(3, order.getBookId());
        ps.setDate(4, order.getDueDate());
    }

    @Override
    protected void prepareForUpd(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getType().ordinal());
        ps.setDate(2, order.getDueDate());
        ps.setInt(3, order.getId());
    }

}
