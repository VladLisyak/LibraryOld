package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.db.HikariConnManager;
import ua.nure.lisyak.SummaryTask4.db.dao.CRUDDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.exception.DAException;

/**
 * Provides a base functionality for all DAO Objects.
 *
 * @param <T> type of DAO that the class is used for 
 */
public abstract class CRUDDAOImpl<T> implements CRUDDAO<T> {

    private static final Logger LOGGER = Logger.getLogger(CRUDDAOImpl.class);
	
    /**
     * Gets an entity of {@code E} type by id.
     *
     * @param id        id of the entity
     * @param sql       query that must have only one prepared parameter
     * @param parser    parser that can get and parse the entity of type {@code E}
     * @param <E>       type of the entity. Must be the child type of {@code T} type the DAO is used for 
     * @return received entity
     */
    protected <E extends T> E getById(int id, String sql, Parser<E> parser) {
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            List<E> result = executeQuery(pst, parser);
            return result.isEmpty() ? null : result.get(0);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Gets a {@link Connection} from connection pool.
     *
     * @return {@link Connection} from connection pool
     */
    protected Connection getConnection() {
        return HikariConnManager.getConnection();
    }

    /**
     * Executes an sql Query
     *
     * @param pst prepared statement for specified query
     * @param parser parser that can get and parse the entity of type {@code E}
     * @param <E>       type of the resulting {@code Entity}
     * @return result entity
     * @throws SQLException if cannot execute query
     */
    protected <E> List<E> executeQuery(PreparedStatement pst, Parser<E> parser) throws SQLException {
        List<E> result = new ArrayList<>();
        try (ResultSet resSet = pst.executeQuery()) {
            while (resSet.next()) {
                E record = parser.parse(resSet);
                result.add(record);
            }
        }
        return result;
    }

    /**
     * Gets all entities of specified type
     *
     * @param sql       query to execute. Must be without prepared statements.
     * @param parser parser that can extract the entity of type {@code E}
     * @param <E>       type of the entities of result list
     * @return list of entities that was found
     */
    protected <E extends T> List<E> getAll(String sql, Parser<E> parser) {
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            return executeQuery(pst, parser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Gets all entities for specified ids
     *
     * @param idS       ids of the entities to be ectracted
     * @param sql       query to execute. Has prepared parameters by size of {@code ids}.
     * @param parser parser that can get and parse the entity of type {@code E}
     * @param <E>       type of the entities of result list
     * @return list of entities that was found
     */
    protected <E> List<E> getAll(List<Integer> idS, String sql, Parser<E> parser) {
    	if (idS == null || idS.size()==0){
        	return new ArrayList<E>();
        }
    	
    	StringBuilder builder = new StringBuilder();
        
        builder.append("(");
        
        for (int i = 0; i < idS.size(); i++) {
            builder.append("?");
            
            if (i + 1 != idS.size()) {
                builder.append(", ");
            }
        }
        
        builder.append(")");
        String nextSQL = sql.replace("{in}", builder.toString());
       
        Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(nextSQL)) {
        	for (int i = 0; i < idS.size(); i++) {
                pst.setInt(i + 1, idS.get(i));
            }
    		
    		LOGGER.debug("Resulting sql is "+ nextSQL);
            
            return executeQuery(pst, parser);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(nextSQL), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Deletes an entity by specified id.
     * @param sql query to execute. One prepared parameter are allowed.
     * @param id  id of the entity to be deleted.
     */
    protected void deleteById(String sql, int id) {
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            LOGGER.debug("Entity info");
            
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Adds a new entity of type the DAO is used for.
     *
     * @param entity entity of parameterized type to be added
     * @param sql    query to execute. See {@link #prepareForIns(Object, java.sql.PreparedStatement)}
     * @return added entity get from DB
     */
    protected T add(T entity, String sql) {
    	Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForIns(entity, pst);
            pst.executeUpdate();
            ResultSet genKeys = pst.getGeneratedKeys();
            genKeys.next();
            int id = genKeys.getInt(1);
            
            LOGGER.debug("Entity added.");
            
            return getById(id);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Adds a new entity and returns only it's id.
     *
     * @param entity parameterized entity to add
     * @param sql    query to execute. The same as for {@link #add(Object)}
     * @return generated id of the entity
     */
    protected int addSimple(T entity, String sql) {
    	Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForIns(entity, pst);
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            
            LOGGER.debug("Entity added.");
            
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Adds a list of entities.
     *
     * @param entities entities to be added
     * @param sql query to execute. The same as for {@link #add(Object)} method
     * @return list of added entities get from DB
     */
    protected List<T> addAll(List<T> entities, String sql) {
    	Connection conn = getConnection();
    	try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (T ent : entities) {
                prepareForIns(ent, pst);
                pst.addBatch();
            }
            pst.executeBatch();
            ResultSet genKeys = pst.getGeneratedKeys();
            List<Integer> idS = new ArrayList<>();
            while (genKeys.next()) {
                idS.add(genKeys.getInt(1));
            }
            
            LOGGER.debug("Entities added.");
            
            return getAll(idS);
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Updates an existing specified entity
     *
     * @param entity entity to be updated
     * @param sql query to execute. See {@link #prepareForUpd(Object, java.sql.PreparedStatement)
     */
    protected void update(T entity, String sql) {
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            prepareForUpd(entity, pst);
            pst.executeUpdate();
            
            LOGGER.debug("Entity updated successfully");
            
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Counts a number of entities. If nothing found returns {@code 0} 
     *
     * @param pst prepared statement for executing the query
     * @return number of entities
     * @throws SQLException if unable to execute query
     */
    protected int count(PreparedStatement pst) throws SQLException {
        try (ResultSet resSet = pst.executeQuery()) {
            if (resSet.next()) {
                return resSet.getInt(1);
            }
            
            LOGGER.debug("No entities found.");
            
            return 0;
        }
    }

    /**
     * Gets a list of objects of the type {@code E}
     *
     * @param pst prepared statement that executes the query
     * @param clazz  class of the objects in the list
     * @param <E> type of the objects in list.
     * @return list of extracted objects
     * @throws SQLException if unable to execute query
     */
    protected <E> List<E> getList(PreparedStatement pst, Class<E> clazz) throws SQLException {
        List<E> ids = new ArrayList<>();
        try (ResultSet resultSet = pst.executeQuery()) {
            while (resultSet.next()) {
                ids.add(resultSet.getObject(1, clazz));
            }
        }
        
        return ids;
    }

    /**
     * Defines if there is entity with specified id in DB
     * @param sql query to execute. One parameter are allowed.
     * @param id  id of the entity to be checked
     *
     * @return {@code true} if the entity exists or {@code false} if it's not 
     */
    protected boolean exists(String sql, int id) {
    	Connection conn = getConnection();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return count(pst) >= 1;
        } catch (SQLException e) {
            LOGGER.warn(getMessage(sql), e);
            throw new DAException(getMessage(sql), e);
        }finally{
        	closeConnection(conn);
        }
    }

    /**
     * Generates a message for {@link Logger} and {@link DAException}
     *
     * @param sql query that has been failed
     * @return message about exceptional situation.
     */
    protected String getMessage(String sql) {
        return "Cannot handle sql ['" + sql + "']. Message: ";
    }
    

    /**
     * Fills parameters for entity to add.
     * The number of parameters must match the number of parameters in the query that adds the entity
     *
     * @param entity  	the entity to add
     * @param pst prepared statement for setting parameters
     * @throws SQLException if unable setting parameter
     */
    protected abstract void prepareForIns(T entity, PreparedStatement pst) throws SQLException;

    /**
    * Fills parameters for entity to update.
     * The number of parameters must match the number of parameters in the query that updates the entity
     *
     * @param entity  	the entity to add
     * @param pst prepared statement for setting parameters
     * @throws SQLException if unable setting parameter
     */
    protected abstract void prepareForUpd(T entity, PreparedStatement pst) throws SQLException;
    
    /**
     * Closes connection passed as parameter.
     * @param connection {@link Connection} to be closed
     */
    protected void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Unable to close Connection", e);
            throw new DAException("Unable to close Connection", e);
        }
    }
}