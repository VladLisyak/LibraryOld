package ua.nure.lisyak.SummaryTask4.db.dao;

import ua.nure.lisyak.SummaryTask4.entity.Subscription;

public interface SubscriptionDAO extends CRUDDAO<Subscription>{
	/**
     * Defines if the specified subscription number exists.
     *
     * @param id subscription's number
     * @return {@code true} if exists, {@code false} in other case
     */
	 public boolean exists(int id);
}
