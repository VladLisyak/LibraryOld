package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.dao.PublisherDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.exception.DAException;


/**
 * DAO for {@link Publisher}s.
 */
public class PublisherDAOImpl extends CRUDDAOImpl<Publisher> implements PublisherDAO{

    private static final Logger LOGGER = Logger.getLogger(PublisherDAOImpl.class);
    
    private static final String SELECT_BY_ID = "SELECT * FROM publishersView WHERE publisher_id=?";
	private static final String SELECT_ALL = "SELECT * FROM publishersView";
	private static final String SELECT_ALL_BY_IDS = "SELECT * FROM publishersView WHERE publisher_id IN {in}";
    private static final String INSERT = "INSERT INTO publishers(title) VALUES(?)";
	private static final String DELETE_BY_ID = "DELETE FROM publishers WHERE id = ?";
	private static final String COUNT_ALL = "SELECT COUNT(*) FROM publishers";
    
    private Parser<Publisher> parser = new Parser<>(Publisher.class);
    
    @Override
    public Publisher add(Publisher publisher) {
        return add(publisher, INSERT);
    }

    @Override
    public void update(Publisher publisher) {
    }

    @Override
    public void delete(int id) {
        deleteById(DELETE_BY_ID, id);
    }

    @Override
    public Publisher getById(int id) {
        return getById(id, SELECT_BY_ID, parser);
    }

    @Override
    public List<Publisher> getAll() {
        return getAll(SELECT_ALL, parser);
    }

    @Override
    public List<Publisher> getAll(List<Integer> idS) {
        return getAll(idS, SELECT_ALL_BY_IDS, parser);
    }

    @Override
    public int countAll() {
        String sql = COUNT_ALL;
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
           return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    @Override
    protected void prepareForIns(Publisher publisher, PreparedStatement pst) throws SQLException {
        pst.setInt(1, publisher.getTitle().getId());
    }

    @Override
    protected void prepareForUpd(Publisher publisher, PreparedStatement pst) throws SQLException {
    }
}
