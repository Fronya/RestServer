package com.fronya.dao.mysql.connection;


import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    private static final String PATH_FILE = "database";
    private static final String RESOURCE_NAME = "db.data_resource_name";

    private static Connection con;
    private static DataSource dataSource;
    private static ConnectionPool instance;
    private static String dataResourceName;

    static {
        ResourceBundle resource = ResourceBundle.getBundle(PATH_FILE);
        dataResourceName = resource.getString(RESOURCE_NAME);
    }

    private ConnectionPool() {
    }

    /**
     * get new connection
     * @return connection
     */
    public static Connection getConnection(){
        if(instance == null){
            synchronized (ConnectionPool.class) {
                try {
                    instance = new ConnectionPool();
                    Context context = new InitialContext();
                    instance.dataSource = (DataSource) context.lookup(dataResourceName);
                    con = dataSource.getConnection();
                    log.debug("get connection pool");
                } catch (NamingException ex) {
                    log.error(ex.getMessage());
                } catch (SQLException ex) {
                    log.error(ex.getMessage());
                }
            }
        }
        return con;
    }
}
