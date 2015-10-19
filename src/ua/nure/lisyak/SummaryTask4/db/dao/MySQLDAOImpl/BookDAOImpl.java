package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.util.Pair;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.dao.BookDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.db.sort.SimpleSortKey;
import ua.nure.lisyak.SummaryTask4.db.sort.SortKey;
import ua.nure.lisyak.SummaryTask4.db.sort.TranslSortKey;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.BookInformation;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.exception.DAException;

/**
 * DAO for {@link Book}s.
 */
public class BookDAOImpl extends CRUDDAOImpl<Book> implements BookDAO{

    private static final Logger LOGGER = Logger.getLogger(BookDAOImpl.class);
    private static final String SELECT_ALL = "SELECT * FROM booksView";
    private static final String SELECT_ALL_BY_ID = "SELECT * FROM booksView WHERE book_id IN {in} ORDER BY book_id";
    private static final String SELECT_ALL_BY_AUTHOR_ID = "SELECT * FROM booksView WHERE author_id=? ORDER BY book_id";
    private static final String SELECT_ALL_INFO_BY_ID = "SELECT id, amount, inStock FROM books WHERE id IN {in}";
    private static final String SELECT_BY_ID = "SELECT * FROM  booksView WHERE book_id=?";
    private static final String COUNT_ALL = "SELECT COUNT(*) FROM books";
    private static final String COUNT_BY_ID = "SELECT COUNT(*) FROM books WHERE id=?";
    private static final String SELECT_IMAGES_BY_PUBLISHER_ID = "SELECT books.image FROM books WHERE books.publisherId=?";
    private static final String SELECT_IMAGES_BY_AUTHOR_ID = "SELECT books.image FROM books WHERE books.authorId=?";
    private static final String INSERT = "INSERT INTO books(title, description, amount, inStock, publisherId, year, pages) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_AUTHORS = "INSERT INTO books_authors(bookId, authorId) VALUES (?, ?)";
    private static final String UPDATE_WITH_IMAGE = "UPDATE books SET amount=?, inStock=?, publisherId=?, year=?, pages=?, image=? WHERE id=?";
    private static final String UPDATE = "UPDATE books SET amount=?, inStock=?, publisherId=?, year=?, pages=? WHERE id=?";
    private static final String UPDATE_STOCK = "UPDATE books SET inStock=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    private static final String DELETE_BOOK_AUTH_BY_ID = "DELETE FROM books_authors WHERE bookId = ?";
    
    private static final String SEARCH = "SELECT * FROM booksView JOIN (SELECT DISTINCT book_id FROM booksView ORDER BY {order} LIMIT ?,?) b ON b.book_id=booksView.book_id ORDER BY booksView.{order}";
    private static final String SEARCH_COUNT = "SELECT COUNT(DISTINCT book_id) FROM booksView";
    private static final String SEARCH_BY_NAME = "SELECT * FROM booksView JOIN (SELECT DISTINCT book_id FROM booksView WHERE ({where}) ORDER BY {order} LIMIT ?,?) b ON b.book_id=booksView.book_id ORDER BY booksView.{order}";
    private static final String SEARCH_BY_NAME_COUNT = "SELECT COUNT(DISTINCT book_id) FROM booksView WHERE ({where})";
	
    
    private Parser<Book> bookParser = new Parser<>(Book.class);
    private Parser<BookInformation> bookInfoParser = new Parser<>(BookInformation.class);
    private Map<String, SortKey> sortTypes;

    
    public BookDAOImpl() {
        sortTypes = new HashMap<>();
        sortTypes.put("default", new SimpleSortKey("book", "id"));
        sortTypes.put("year", new SimpleSortKey("book", "year"));

        sortTypes.put("author", new TranslSortKey("author_name"));
        sortTypes.put("title", new TranslSortKey("book_title"));
        sortTypes.put("publisher", new TranslSortKey("publisher_title"));
    }
    
    @Override
    public Book add(Book book) {
        int nBook = addSimple(book, INSERT);
        book.setId(nBook);
        addAuthors(book);
        return getById(nBook);
    }

    @Override
    public void update(Book book) {
        update(book, UPDATE);
        updateAuthors(book);
    }

    public void updateWithImg(Book book) {
        String sql = UPDATE_WITH_IMAGE;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            prepareForUpdateWithImg(book, pst);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    @Override
    public void delete(int id) {
        deleteById(DELETE_BY_ID, id);
    }

    @Override
    public Book getById(int id) {
        return getById(id, SELECT_BY_ID, bookParser);
    }

    @Override
    public List<Book> getAll() {
        return getAll(SELECT_ALL, bookParser);
    }

    @Override
    public List<Book> getAll(List<Integer> ids) {
            return getAll(ids, SELECT_ALL_BY_ID, bookParser);
    }

    public List<BookInformation> getAllInfo(List<Integer> idS) {
        return getAll(idS, SELECT_ALL_INFO_BY_ID, bookInfoParser);
    }

    public List<Book> getAll(int authorId) {
        String sql = SELECT_ALL_BY_AUTHOR_ID;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, authorId);
            return executeQuery(pst, bookParser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    @Override
    public Pair<List<Book>, Integer> search(int offset, int count, String search, String sortBy, String[] locales, String locale) {
        SortKey order = sortTypes.get(sortBy);
        if (order == null) {
            order = sortTypes.get("default");
        }
        LOGGER.debug(locales);
        String orderBy = order.getSort(locale);
        LOGGER.debug(search);
        return search == null ? search(offset, count, orderBy)
                : searchByName(offset, count, search, orderBy, locales);
    }
    
    public List<String> getImgsByAuthor(int authorId) {
        String sql = SELECT_IMAGES_BY_AUTHOR_ID;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, authorId);
            return getList(pst, String.class);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    
    public List<String> getImgsByPublisher(int publisherId) {
        String sql = SELECT_IMAGES_BY_PUBLISHER_ID;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, publisherId);
            return getList(pst, String.class);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    
    @Override
    public Map<String, SortKey> getSortKeys() {
        return sortTypes;
    }

    public void updateStock(int bookId, int quantity) {
        String sql = UPDATE_STOCK;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, quantity);
            pst.setInt(2, bookId);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public boolean exists(int id) {
        return exists(COUNT_BY_ID, id);
    }

    public int countAll() {
        String sql = COUNT_ALL;
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            return count(pst);
        } catch (SQLException e) {
            LOGGER.warn(getConnection(), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    @Override
    protected void prepareForIns(Book book, PreparedStatement pst) throws SQLException {
        pst.setInt(1, book.getTitle().getId());
        pst.setInt(2, book.getDescription().getId());
        pst.setInt(3, book.getAmount());
        pst.setInt(4, book.getAmount());
        pst.setInt(5, book.getPublisher().getId());
        pst.setInt(6, book.getYear());
        pst.setInt(7, book.getPages());
    }

    @Override
    protected void prepareForUpd(Book book, PreparedStatement pst) throws SQLException {
        Publisher publisher = book.getPublisher();
        pst.setInt(1, book.getAmount());
        pst.setInt(2, book.getInStock());
        if (publisher == null) {
            pst.setObject(3, null);
        } else {
            pst.setInt(3, publisher.getId());
        }
        pst.setInt(4, book.getYear());
        pst.setInt(5, book.getPages());
        pst.setInt(6, book.getId());
    }

    public void prepareForUpdateWithImg(Book book, PreparedStatement pst) throws SQLException {
        Publisher publisher = book.getPublisher();
        pst.setInt(1, book.getAmount());
        pst.setInt(2, book.getInStock());
        if (publisher == null) {
            pst.setObject(3, null);
        } else {
            pst.setInt(3, publisher.getId());
        }
        pst.setInt(4, book.getYear());
        pst.setInt(5, book.getPages());
        pst.setString(6, book.getImage());
        pst.setInt(7, book.getId());
    }

    public void addAuthors(Book book) {
        String sql = INSERT_AUTHORS;
        List<Author> authors = book.getAuthors();
        if (authors == null) {
            return;
        }
        Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
               for (int i = 0; i < book.getAuthors().size(); i++) {
                pst.setInt(1, book.getId());
                pst.setInt(2, authors.get(i).getId());
                pst.addBatch();
            }
            pst.executeBatch();
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    public void updateAuthors(Book book) {
        deleteById(DELETE_BOOK_AUTH_BY_ID, book.getId());
        addAuthors(book);
    }

    
    private Pair<List<Book>, Integer> search(int offset, int count, String sortBy) {
        String sql = SEARCH;
        sql = sql.replace("{order}", sortBy);
        List<Book> booksList = getBooks(sql, offset, count);
        Set<Book> booksSet = new LinkedHashSet<Book>();
        booksSet.addAll(booksList);
        booksList= new ArrayList<Book>();
        booksList.addAll(booksSet);
        sql = SEARCH_COUNT;
        return executeCount(booksList, sql, count);
    }

    private Pair<List<Book>, Integer> searchByName(int offset, int count, String search, String sortBy, String[] locales) {
        String sql = SEARCH_BY_NAME;
        String searchString = search.replace("'", "''");
        sql = sql.replace("{order}", sortBy);
        StringBuilder strBuilder = new StringBuilder();
        if (locales!=null){
		    for (int i = 0; i < locales.length - 1; i++) {
		        strBuilder.append("book_title_").append(locales[i]).append(" LIKE '%").append(searchString).append("%' OR ");
		    }
		    strBuilder.append("book_title_").append(locales[locales.length - 1]).append(" LIKE '%").append(searchString).append("%' ");    
		    sql = sql.replace("{where}", strBuilder.toString());
        }
        List<Book> books = getBooks(sql, offset, count);
        sql = SEARCH_BY_NAME_COUNT;
        sql = sql.replace("{where}", strBuilder.toString());
        return executeCount(books, sql, count);
    }
    
    public List<Book> getBooks(final String sql, int offset, int count) {
    	Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, offset);
            pst.setInt(2, count);
            return executeQuery(pst, bookParser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    private Pair<List<Book>, Integer> executeCount(List<Book> booksList, final String sql, int count) {
    	Connection conn = getConnection();
    		try (PreparedStatement pst = conn.prepareStatement(sql)) {
    		ResultSet resSet = pst.executeQuery();
            if (resSet.next()) {
                int size = resSet.getInt(1);
                return new Pair<>(booksList, (int) Math.ceil(size * 1.0 / count));
            } else {
                return new Pair<>(booksList, 0);
            }
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

}
