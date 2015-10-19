package ua.nure.lisyak.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.OrderDetails;
import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;

public interface OrderDAO extends CRUDDAO<Order>{

	 /**
     * Gets all {@link Order}s of specified type by number of reader's subscription .
     *
     * @param subsId id of particular subscription
     * @param type order's type
     * @return {@link List} of orders found
     */
    public List<OrderDetails> getAll(int subsId, OrderType type);
    
    /**
     * Gets all orders of specified {@link OrderType}
     * 
     * @return {@link List} of orders found
     */
    public List<Order> getAll(OrderType type);
    
    /**
     * Counts total orders where books 
     * are written by author with specified id
     * 
     * @param authId Author's id 
     * @return number of orders where books 
     * are written by author with specified id
     */
    public int countByAuthor(int authId);
    
    /**
     * Counts total orders where books 
     * are published by publisher with specified id
     * 
     * @param pubId {@link Publisher}s id 
     * @return number of orders where books 
     * are published by {@link Publisher}} with specified id
     */
    public int countByPublisher(int pubId);
    
    /**
     * Counts total orders where books has specified id
     * 
     * @param bookId Book's id 
     * @return number of orders with specified books id
     */
    public int countByBook(int bookId);
    
    /**
     * Counts total orders of specified type.
     *
     * @param type {@link Order}s type
     * @return number of orders of specified type
     */
    public int countByType(OrderType type);

    /**
     * Updates overdued orders.
     */
    public void updateOrders();
    
    /**
     * Adds all specified orders to database
     * @param ordList list of orders to be added
     * @return resulting list of added orders.
     */
    public List<Order> addAll(List<Order> ordList);

    /**
     * Updates orders made in reading rooms.
     */
    public void updOrdersInReadingRooms();

    /**
     * Deletes overdue orders.
     */
    public void deleteOverdueOrders();


}
