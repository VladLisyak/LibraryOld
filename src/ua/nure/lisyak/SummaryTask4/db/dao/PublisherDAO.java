package ua.nure.lisyak.SummaryTask4.db.dao;

import ua.nure.lisyak.SummaryTask4.entity.Publisher;

public interface PublisherDAO extends CRUDDAO<Publisher>{
	 /**
     * Counts the total number of {@link Publisher}s.
     *
     * @return total count of publishers
     */
	 public int countAll();
}
