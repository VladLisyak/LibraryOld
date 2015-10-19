package ua.nure.lisyak.SummaryTask4.service;


import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Publisher;

/**
 * Service for working with publishers.
 */
public interface PublisherService {

    /**
     * Adds a new {@link Publisher}.
     *
     * @param pub {@link Publisher} to add
     * @return added {@link Publisher} entity
     */
    Publisher add(Publisher pub);

    Publisher getById(int id);

    List<Publisher> getAll();

    /**
     * Updates an existing {@link Publisher} entity.
     *
     * @param pub {@link Publisher} to update.
     */
    void update(Publisher pub);

    /**
     * Deletes {@link Publisher}.
     *
     * @param pub {@link Publisher} to delete.
     */
    void delete(Publisher pub);

    /**
     * Gets total count of {@link Publisher}s.
     *
     * @return count of {@link Publisher}s
     */
    int countAll();

}
