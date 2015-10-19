package ua.nure.lisyak.SummaryTask4.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection manager that uses Hikari Connection Pool
 */
public class HikariConnManager{
    private static final Logger LOGGER = Logger.getLogger(HikariConnManager.class);
    private static HikariDataSource pooledDataSource;
    
    private static final boolean CACHE_PSTATEMENTS = true;
    private static final int PST_CACHE_SIZE = 200;
    private static final int CACHE_SQL_LIMIT = 1024;
    private static final boolean USE_SERVER_PST = true;

    public static Connection getConnection() {
        if (pooledDataSource == null) {
            LOGGER.info("Load pooled data source");
            Properties properties = new Properties();
            InputStream inputStream = HikariConnManager.class.getClassLoader().getResourceAsStream(Constants.ROUTES.DATABASE_PROPS_PATH);
            try {
                properties.load(inputStream);
            } catch (IOException e) {
            	LOGGER.error("Error occured while creating cinnection", e);
            }
            
            HikariConfig config = new HikariConfig();
            config.setDataSourceClassName(properties.getProperty("driver"));
            config.setInitializationFailFast(true);
            config.addDataSourceProperty("serverName", properties.getProperty("server"));
            config.addDataSourceProperty("port", properties.getProperty("port"));
            config.addDataSourceProperty("databaseName", properties.getProperty("database"));
            config.addDataSourceProperty("user", properties.getProperty("user"));
            config.addDataSourceProperty("password", properties.getProperty("password"));
            config.addDataSourceProperty("cachePrepStmts", CACHE_PSTATEMENTS);
            config.addDataSourceProperty("prepStmtCacheSize", PST_CACHE_SIZE);
            config.addDataSourceProperty("prepStmtCacheSqlLimit", CACHE_SQL_LIMIT);
            config.addDataSourceProperty("useServerPrepStmts", USE_SERVER_PST);
            config.setTransactionIsolation("TRANSACTION_" + properties.getProperty("transactionIsolation"));
            
            pooledDataSource = new HikariDataSource(config);
            pooledDataSource.setDriverClassName(properties.getProperty("driver"));
           
        }
        Connection connection = null;
        try {
            connection = pooledDataSource.getConnection();
        } catch (SQLException e) {
        	  LOGGER.error("Error occured while creating connection", e);
        }
        return connection;
    }
    
    public static void stop() {
        pooledDataSource.close();
    }
    
    private HikariConnManager(){	
    }
}

