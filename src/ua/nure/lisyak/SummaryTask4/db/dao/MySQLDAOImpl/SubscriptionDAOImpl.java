package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.SubscriptionDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.Subscription;


/**
 * A DAO for {@link Subscription}s.
 */
public class SubscriptionDAOImpl extends CRUDDAOImpl<Subscription> implements SubscriptionDAO {

    private Parser<Subscription> parser = new Parser<>(Subscription.class);

    private static final String INSERT_SUBSCRIPTION = "INSERT INTO subscriptions(userId, expirationDate) VALUES(?, ?)";
	private static final String UPDATE_SUBSCRIPTION = "UPDATE subscriptions SET expirationDate=? WHERE userId=?";
	private static final String SELECT_ALL = "SELECT * FROM subscriptions";
    private static final String SELECT_ALL_BY_ID = "SELECT * FROM subscriptions WHERE id IN {in}";
	private static final String COUNT_BY_ID = "SELECT COUNT(*) FROM subscriptions WHERE id=?";

    @Override
    public Subscription add(Subscription subs) {
        return add(subs, INSERT_SUBSCRIPTION);
    }

    @Override
    public void update(Subscription subs) {
        update(subs, UPDATE_SUBSCRIPTION);
    }

    @Override
    public void delete(int id) {
    	return;
    }

    @Override
    public Subscription getById(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAll() {
        return getAll(SELECT_ALL, parser);
    }

    @Override
    public List<Subscription> getAll(List<Integer> idS) {
        return getAll(idS, SELECT_ALL_BY_ID, parser);
    }

    public boolean exists(int id) {
        return exists(COUNT_BY_ID, id);
    }

    @Override
    protected void prepareForIns(Subscription subscription, PreparedStatement pst) throws SQLException {
        pst.setInt(1, subscription.getUserId());
        pst.setDate(2, subscription.getExpirationDate());
    }

    @Override
    protected void prepareForUpd(Subscription subscription, PreparedStatement pst) throws SQLException {
        pst.setDate(1, subscription.getExpirationDate());
        pst.setInt(2, subscription.getUserId());
    }
}
