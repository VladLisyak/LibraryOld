package ua.nure.lisyak.SummaryTask4.db.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import ua.nure.lisyak.SummaryTask4.db.sort.SortKey;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.BookInformation;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;

/**
 *  Interface for DAO objects are manipulating {@link Book} objects
 */
public interface BookDAO extends CRUDDAO<Book>{
	
	/**
     * Updates a book and it's image.
     *
     * @param book book to be updated
     */
	void updateWithImg(Book book);
	
	/**
     * Gets all info about books with specified ids
     *
     * @param idS ids of the books
     * @return list of books info
     */
	List<BookInformation> getAllInfo(List<Integer> idS);
	
	/**
     * Searches books by pages.
     *
     * @param offset  number of books that was get previously
     * @param count   number of books to get
     * @param search  search string
     * @param sortBy  parameter of sorting
     * @param locales all available locales
     * @param locale  current locale
     * @return  pair (first element - found books, second - number of books that awaits for getting
     * 
     * @see Pair
     */
	Pair<List<Book>, Integer> search(int offset, int count, String search, String orderBy, String[] locales, String locale);
	
	/**
     * Gets all books of particular author.
     *
     * @param authorId id of the author whose books need to be found
     * @return list of authors books
     */
	List<Book> getAll(int authorId);
	
	/**
     * Gets images of all books written by author with id that gets as parameter.
     *
     * @param authorId authors id
     * @return list of all found images
     */
	List<String> getImgsByAuthor(int authorId);
	
	/**
     * Gets images of all {@link Book}s published by {@link Publisher} with specified id.
     *
     * @param publisherId publishers id
     * @return list of all found images
     */
	List<String> getImgsByPublisher(int publisherId);
	

    /**
     * Updates the number of available books in stock with specified id.
     *
     * @param bookId   {@link Book}s' id
     * @param quantity new number of {@link Book}s in stock
     */
	void updateStock(int bookId, int quantity);
	
	/**
     * Defines whether the {@link Book} with specified id exists.
     *
     * @param id books id.
     * @return {@code true} if book exists, and {@code false} in other case.
     */
	boolean exists(int id);
	
	/**
     * Counts all {@link Book}s.
     *
     * @return books count
     */
	int countAll();
	
	/**
	 * Prepares a statement for updating paticular Book and its image
	 * @param book the book to be updated
	 * @param pst statement that should be prepared
	 * @throws SQLException
	 */
	void prepareForUpdateWithImg(Book book, PreparedStatement pst) throws SQLException;

	/**
     * Adds authors to specified book.
     * 
     * @param book the {@link Book} that the {@link Author}s should be added to.
     */
    void addAuthors(Book book);

    /**
     * Updates authors of specified book.
     * 
     * @param book the {@link Book} which {@link Author}s should be updated.
     */
    void updateAuthors(Book book);

    List<Book> getBooks(final String sql, int offset, int count);

    /**
     * Gets available sort types.
     *
     * @return map of available sort types
     */
	Map<String, SortKey> getSortKeys();

}
