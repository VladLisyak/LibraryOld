package ua.nure.lisyak.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.BookUser;
import ua.nure.lisyak.SummaryTask4.entity.Role;

public interface UserDao  extends CRUDDAO<User>{
	public Reader getReaderById(int id);
	public User getByLogin(String login, int excludeId);
	public User getByEmail(String email, int excludeId);
	public List<User> getByRole(Role role);
	public List<Reader> getAllReaders();

    /**
     * Counts the number of users of specified role.
     *
     * @param role user role
     * @return the number of users of specified role
     */
    public int countByRole(Role role);

    /**
     * Updates subscriptions of readers.
     */
    public void updateSubscriptions();

    /**
     * Get books by order type.
     *
     * @return list of overdue books
     */
    public List<BookUser> getOrders();
}
