package ua.nure.lisyak.SummaryTask4.service;

import java.util.List;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.OrderDetails;
import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.entity.Reader;

/**
 * Service for working with {@link Order}s.
 */
public interface OrderService {

    /**
     * Counts the number of ordered {@link Book}s written by specified {@link author}.
     *
     * @param author {@link Author} of books to be counted
     * @return number of ordered books written by the {@link Author}
     */
    int countByAuthor(Author author);
    
    public void delete(int id);

    /**
     * Counts the number of ordered books published by the {@link} Publisher}.
     *
     * @param publisher publisher of the books
     * @return number of ordered books written by the {@link Publisher}
     */
    int countByPub(Publisher publisher);

    /**
     * Counts the number of times the {@link Book} was ordered.
     *
     * @param book book which orders needs count
     * @return the number of times the {@link Book} was ordered
     */
    int countByBook(Book book);

    /**
     * Counts the number of {@link Order}s of specified {@link OrderType}
     *
     * @param type {@link OrderType} of orders to count
     * @return the number of orders of specified {@link OrderType}
     */
    int countByType(OrderType type);

    /**
     * Adds new {@link Order}.
     *
     * @param order {@link Order} to add
     * @return added {@link Order}
     */
    Order add(Order order);

    /**
     * Adds new orders of the {@link Reader}. Executes within a transaction.
     *
     * @param reader       the reader who ordered the books
     * @param orders ids of ordered books
     * @return list of created entities.
     */
    List<Order> add(Reader reader, List<Integer> orders);

    /**
     * Gets detailed information about the {@link Order}s made by the {@link Reader}.
     *
     * @param reader {@link Reader} who made order.
     * @return a {@link List} of orders of the {@link Reader}
     */
    List<OrderDetails> getOrdered(Reader reader);

    /**
     * Gets detailed information about the {@link Book}s checked out by the {@link Reader}.
     *
     * @param reader {@link Reader} who checked out books
     * @return detailed information about the books checked out by the {@link Reader}
     */
    List<OrderDetails> getCheckedOut(Reader reader);

    /**
     * Updates an existing {@link Order}.
     *
     * @param order order to update.
     */
    void update(Order order);

    void updateOrders();

    Order getById(int id);

    /**
     * Gets all orders of the specified {@link OrderType}.
     *
     * @param type type of the {@link Order}s
     * @return all {@link Order}s of the specified {@link OrderType}
     */
    List<Order> getAll(OrderType type);

}
