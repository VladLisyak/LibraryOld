package ua.nure.lisyak.SummaryTask4.service.servicesImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.BookDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.OrderDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.BookDAOImpl;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.OrderDAOImpl;
import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.BookInformation;
import ua.nure.lisyak.SummaryTask4.entity.OrderDetails;
import ua.nure.lisyak.SummaryTask4.entity.Order;
import ua.nure.lisyak.SummaryTask4.entity.OrderType;
import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.service.OrderService;
import ua.nure.lisyak.SummaryTask4.util.Constants;

/**
 * Provides implementation of {@link OrderService} interface for manipulating with {@link Order} entities.
 */
public final class OrderServiceImpl implements OrderService {

   
    private OrderDAO orderDAO = new OrderDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    public int countByAuthor(Author author) {
        return orderDAO.countByAuthor(author.getId());
    }

    @Override
    public int countByPub(Publisher publisher) {
        return orderDAO.countByPublisher(publisher.getId());
    }

    @Override
    public int countByBook(Book book) {
        return orderDAO.countByBook(book.getId());
    }
    
    public void delete(int id){
    	orderDAO.delete(id);
    }

    @Override
    public int countByType(OrderType type) {
        return orderDAO.countByType(type);
    }

    @Override
    public Order add(Order order) {
        return orderDAO.add(order);
    }

    @Override
    public List<Order> add(Reader reader, List<Integer> ids) {
        List<Order> orders = new ArrayList<>();
        List<BookInformation> bookInfo = bookDAO.getAllInfo(ids);
        for (BookInformation info : bookInfo) {
            if (info.getInStock() < 1) {
                return null;
            }
            bookDAO.updateStock(info.getId(), info.getInStock() - 1);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DATE, Constants.Settings.ORDER_DURATION);
        Date dueDate = new Date(calendar.getTime().getTime());
        for (Integer bookId : ids) {
                Order order = new Order();
                order.setBookId(bookId);
                order.setType(OrderType.ORDERED);
                order.setDueDate(dueDate);
                order.setSubscriptionId(reader.getSubscription().getId());
                
                orders.add(order);
        }

        return orderDAO.addAll(orders);
    }

    @Override
    public List<OrderDetails> getOrdered(Reader reader) {
        return orderDAO.getAll(reader.getSubscription().getId(), OrderType.ORDERED);
    }

    @Override
    public List<OrderDetails> getCheckedOut(Reader reader) {
        return orderDAO.getAll(reader.getSubscription().getId(), OrderType.CHECKED_OUT);
    }

    @Override
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Override
    public void updateOrders() {
        orderDAO.updateOrders();
        orderDAO.deleteOverdueOrders();
        orderDAO.updOrdersInReadingRooms();
    }

    @Override
    public Order getById(int id) {
        return orderDAO.getById(id);
    }

    @Override
    public List<Order> getAll(OrderType type) {
        return orderDAO.getAll(type);
    }
}
