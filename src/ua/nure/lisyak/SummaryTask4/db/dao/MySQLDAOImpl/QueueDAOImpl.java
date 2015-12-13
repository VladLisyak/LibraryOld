package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.dao.QueueDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.Queue;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.exception.DAException;

public class QueueDAOImpl extends CRUDDAOImpl<Queue> implements QueueDAO {
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    
	private static final String SELECT_ALL_BY_BOOK_ID = "SELECT * FROM queue WHERE bookId = (?)";
	private static final String DELETE_QUEUE = "DELETE FROM queue WHERE id = ?";
	private static final String INSERT_QUEUE = "INSERT INTO queue (userId, bookId) VALUES (?, ?)";
	private static final String SELECT_QUEUE_BY_ID = "SELECT * FROM queue WHERE id = ?";
	
	private Parser<Queue> parser = new Parser<>(Queue.class);
	

	@Override
	public void update(Queue entity) {
		throw new UnsupportedOperationException();	
	}

	@Override
	public List<Queue> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Queue> getAll(List<Integer> idS) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Queue getById(int id) {
		 return getById(id, SELECT_QUEUE_BY_ID, parser);
	}
	
	@Override
	protected void prepareForUpd(Queue entity, PreparedStatement pst)
			throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	
	

	@Override
	public List<Queue> getByBookId(int Id) {
		 String sql = SELECT_ALL_BY_BOOK_ID;
	        Connection conn = getConnection();
	    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
	            pst.setInt(1, Id);
	            return executeQuery(pst, parser);
	        } catch (SQLException e) {
	            LOGGER.warn(getMessage(sql), e);
	            throw new DAException(getMessage(sql), e);
	        }finally{
	        	closeConnection(conn);
	        }
	}
	
	@Override
	public Queue add(Queue entity) {
		return add(entity, INSERT_QUEUE);
	}
	
	@Override
	public void delete(int id) {
		deleteById(DELETE_QUEUE, id);	
	}

	@Override
	protected void prepareForIns(Queue entity, PreparedStatement pst)
			throws SQLException {
		pst.setInt(1, entity.getUserId());
        pst.setInt(2, entity.getBookId());
	}

	
	
	
    
}
