package ua.nure.lisyak.SummaryTask4.service;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Reader;
import ua.nure.lisyak.SummaryTask4.entity.User;
import ua.nure.lisyak.SummaryTask4.entity.BookUser;
import ua.nure.lisyak.SummaryTask4.entity.Role;

/**
 * Service for working with {@link User}s.
 */
public interface UserService {

    /**
     * Adds a new {@link User}.
     *
     * @param user {@link User} entity to add
     * @return added user as result of adding
     */
    User add(User user);

    /**
     * Updates an existing {@link User}.
     *
     * @param user {@link User} entity to update.
     */
    void update(User user);

    /**
     * Confirms registered reader account.
     *
     * @param user {@link Reader} to confirm
     */
    void confirm(User user);

    /**
     * Deletes {@link User}.
     *
     * @param user {@link User} to delete
     */
    void delete(User user);

    /**
     * Gets {@link User} entity by {@code id}.
     *
     * @param id id of {@link User} 
     * @return found user
     */
    User getById(int id);

    /**
     * Gets {@link Reader} by id.
     *
     * @param user {@link Reader} entity with the specified id
     * @return {@link Reader} found
     */
    Reader getReaderById(User user);

    /**
     * Gets {@link Reader} entity by id
     *
     * @param id id of the {@link Reader}
     * @return {@link Reader} found
     */
    Reader getReaderById(int id);

    /**
     * Defines if the {@link User} with specified subscription exists.
     *
     * @param subsId {@link Subscription} id
     * @return {@code true} if exists, {@code false} if it's not.
     */
    boolean readerExists(int subsId);

    /**
     * Gets {@link User} entity by login.
     *
     * @param login login of {@link User}
     * @return {@link User} found
     */
    User getByLogin(String login);

    /**
     * Gets {@link User} entity by login. Excluding user with specified id.
     *
     * @param login  login of {@link User}
     * @param ignoreId id of user that must be excluded 
     * @return {@link User} found
     */
    User getByLogin(String login, int ignoreId);

    /**
     * Gets user by email.
     *
     * @param email     email of {@link User}
     * @param ignoreId id of {@link User} that must be excluded
     * @return {@link User} found
     */
    User getByEmail(String email, int ignoreId);

    /**
     * Gets all users of specified {@link Role}.
     *
     * @param role user {@link Role}
     * @return list of found {@link Users}
     */
    List<User> getByRole(Role role);

    /**
     * Counts the number of users of specified {@link Role}.
     *
     * @param role user {@link Role}
     * @return total count of users of specified {@link Role}
     */
    int countByRole(Role role);

    /**
     * Gets all {@link Reader}s.
     *
     * @return list of {@link Readers} found 
     */
    List<Reader> getAllReaders();

    /**
     * Updates {@link Subscription}s of {@link Reader}s.
     */
    void updateSubs();

    /**
     * Updates subscription of {@link Reader}
     *
     * @param reader {@link Reader} whose subscription needs updating
     */
    void updateSubs(Reader reader);

    /**
     * Selects all {@link Book}s from overdued {@link Order}s
     *
     * @return list of {@link Books} that user hasn't returned in time
     */
    List<BookUser> getBooks();

}
