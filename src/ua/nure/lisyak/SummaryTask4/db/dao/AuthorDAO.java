package ua.nure.lisyak.SummaryTask4.db.dao;

import ua.nure.lisyak.SummaryTask4.entity.Author;

/**
 *  An interface for DAO object for manipulating by {@link Author} entities
 */
public interface AuthorDAO extends CRUDDAO<Author>{
	/**
     * Counts the total number of authors.
     *
     * @return the total number of authors.
     */
	public int countAll();
}
