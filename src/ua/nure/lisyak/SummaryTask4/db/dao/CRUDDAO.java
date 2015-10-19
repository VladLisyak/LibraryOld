package ua.nure.lisyak.SummaryTask4.db.dao;

import java.util.List;

import javax.naming.OperationNotSupportedException;
/**
 * Base interface for classes that are used for working with database
 * @param <T> type for which current DAO are used
 */

public interface CRUDDAO<T> {

    /**
     * Adds a new entity.
     * @param entity entity to be added
     * @return added entity from db
     * @throws OperationNotSupportedException 
     */
    T add(T entity);

    /**
     * Updates existing entity
     * @param entity entity to be updated
     * @throws OperationNotSupportedException 
     */
    void update(T entity);

    /**
     * Deletes entity by it's id
     * @param id id of the entity to be deleted
     * @throws OperationNotSupportedException 
     */
    void delete(int id);

    /**
     * Gets an entity by it's id.
     * @param id id of the entity to get
     * @return the entity with specified id
     */
    T getById(int id);

    /**
     * Gets all objects of specified type
     * @return all entities of type for which DAO are used
     */
    List<T> getAll();

    /**
     * Gets all entities with the specified ids
     * @param idS ids of the entities
     * @return all entities with ids from {@link List}
     * @throws OperationNotSupportedException 
     */
    List<T> getAll(List<Integer> idS);
    
}
