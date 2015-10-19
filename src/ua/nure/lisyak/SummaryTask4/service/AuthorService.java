package ua.nure.lisyak.SummaryTask4.service;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Author;

/**
 * Service for working with {@link Author}s.
 */
public interface AuthorService {

    /**
     * Adds a new author.
     *
     * @param author author entity to add.
     * @return resulting added author
     */
    Author add(Author author);

    /**
     * Gets author by id.
     *
     * @param id id of {@link Author} to get
     * @return author Entity
     */
    Author getById(int id);

    /**
     * Gets all {@link Authors}.
     *
     * @return list of authors
     */
    List<Author> getAll();

    /**
     * Gets all authors with specified ids
     *
     * @param idS ids of authors
     * @return list of found authors
     */
    List<Author> getAll(List<Integer> idS);

    /**
     * Updates an existing {@ Author}.
     *
     * @param author {@link Author} entity to update.
     */
    void update(Author author);

    /**
     * Deletes author.
     *
     * @param author {@link Author} entity to delete.
     */
    void delete(Author author);

    /**
     * Counts the number of all {@link Authors}.
     *
     * @return total count of authors
     */
    int countAll();

}
