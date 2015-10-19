package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.dao.AuthorDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.exception.DAException;

/**
 * DAO for authors.
 */
public class AuthorDAOImpl extends CRUDDAOImpl<Author> implements AuthorDAO{

     private static final Logger LOGGER = Logger.getLogger(AuthorDAOImpl.class);
     
     private static final String SELECT_BY_ID = "SELECT * FROM authorsView WHERE author_id=?";
     private static final String SELECT_ALL = "SELECT * FROM authorsView";
     private static final String SELECT_ALL_BY_IDS = "SELECT * FROM authorsView WHERE author_id IN {in}";
     private static final String INSERT = "INSERT INTO authors(name, description) VALUES(?, ?)";
     private static final String UPDATE = "UPDATE authors SET image=? WHERE id=?";
     private static final String DELETE_BY_ID = "DELETE FROM authors WHERE id = ?";
     private static final String COUNT_ALL = "SELECT COUNT(*) FROM authors";
	
	 private Parser<Author> authorParser = new Parser<>(Author.class);
	    
    @Override
    public Author add(Author author) {
        return add(author, INSERT);
    }

    @Override
    public void update(Author author) {
        update(author, UPDATE);
    }

    @Override
    public void delete(int id) {
        deleteById(DELETE_BY_ID, id);
    }

    @Override
    public Author getById(int id) {
        return getById(id, SELECT_BY_ID, authorParser);
    }

    @Override
    public List<Author> getAll() {
        return getAll(SELECT_ALL, authorParser);
    }

    @Override
    public List<Author> getAll(List<Integer> ids) {
        return getAll(ids, SELECT_ALL_BY_IDS, authorParser);
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
    protected void prepareForIns(Author author, PreparedStatement pst) throws SQLException {
    	LOGGER.info("Preparing for insert.");
        pst.setInt(1, author.getName().getId());
        pst.setInt(2, author.getDescription().getId());
        LOGGER.info("Statement ready for insert.");
    }

    @Override
    protected void prepareForUpd(Author author, PreparedStatement pst) throws SQLException {
    	LOGGER.info("Preparing for update.");
    	pst.setString(1, author.getImage());
        pst.setInt(2, author.getId());
        LOGGER.info("Statement ready for update.");
    }

}
