package ua.nure.lisyak.SummaryTask4.service;

import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import ua.nure.lisyak.SummaryTask4.db.sort.SortKey;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;;

/**
 * Service for working with {@link Book}s.
 */
public interface BookService {

    /**
     * Adds a new {@link Book}. Executes within a transaction.
     *
     * @param book {@link Book} to add
     * @return added {@link Book}
     */
    Book add(Book book);

    /**
     * Updates an existing {@link Book}. Executes within a transaction.
     *
     * @param book book that needs updating
     */
    void update(Book book);

    /**
     * Updates an existing book and its image. Executes within a transaction.
     *
     * @param book {@link Book} that needs updating
     */
    void updateWithImg(Book book);

    /**
     * Deletes {@link Book}. Executes within a transaction.
     *
     * @param book {@link Book} to be deleted
     */
    void delete(Book book);

    /**
     * Defines if a book with specified id exists.
     *
     * @param id book's id.
     * @return {@code true} if book exists, {@code false} if its not.
     */
    boolean exists(int id);

    /**
     * Gets all {@link Book}s.
     *
     * @return list of found books
     */
    List<Book> getAll();

    List<Book> getAll(List<Integer> idS);

    /**
     * Gets all books of specified author.
     *
     * @param author books {@link Author}
     * @return list of {@link Book}s found 
     */
    List<Book> getAll(Author author);

    /**
     * Gets the total number of books.
     *
     * @return total count of books
     */
    int countAll();

    /**
     * Gets images of all books written by 
     * {@link Author} with specified id.
     *
     * @param author {@link Author} of books
     * @return list of images  found
     */
    List<String> getImgsByAuthor(Author author);

    /**
     * Gets images of all books published by {@link Publisher} with specified id.
     *
     * @param publisher {@link Publisher} of books
     * @return list of images found
     */
    List<String> getImgsByPub(Publisher publisher);

    /**
     * Gets {@link Book} by id.
     *
     * @param id id of {@link Book}
     * @return book with specified id
     */
     Book getById(int id);

    /**
     * Method for searching books based on set of parameters.
     *
     * @param viewedPage   page that was already viewed
     * @param booksPerPage number of books to be viewed per page
     * @param search       search query
     * @param sortBy       parameter that specifies sorting order
     * @param locales      set of available locales
     * @param locale       current locale
     * @return pair - first element - books found, second - number of unshowed books 
     */
     public Pair<List<Book>, Integer> getAll(int viewedPage, int booksPerPage, String search, String sortBy, String[] locales, String locale);

    /** 
     * Gets available sort types.
     *
     * @return {@link Map} of available sort orders.
     */

     public Map<String, SortKey> getSortKeys();


}
