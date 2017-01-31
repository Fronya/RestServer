package com.fronya.dao.mysql.customer;


import com.fronya.dao.customer.CustomerDao;
import com.fronya.dao.mysql.connection.ConnectionPool;
import com.fronya.model.customer.Customer;
import com.fronya.model.customer.RegisterCustomer;
import com.fronya.model.role.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MysqlCustomerDao implements CustomerDao{
    private static final Logger log = Logger.getLogger(MysqlCustomerDao.class);

    private static final String PATH_FILE = "database";
    private static final String COLUMN_ID = "id_customer";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ROLE = "id_role";
    private static final String COLUMN_PASSWORD = "password";

    private static final String KEY_REGISTER = "customer.register";
    private static final String KEY_LOGIN = "customer.login";
    private static final String KEY_GET_ID = "customer.get_id";

    private static String queryRegister;
    private static String queryLogin;
    private static String queryGetId;

    static{
        ResourceBundle resource = ResourceBundle.getBundle(PATH_FILE);
        queryRegister = resource.getString(KEY_REGISTER);
        queryLogin = resource.getString(KEY_LOGIN);
        queryGetId = resource.getString(KEY_GET_ID);
    }

    /**
     * Register new customer
     * @param newCustomer new user
     * @return is register user or no
     */
    @Override
    public int create(RegisterCustomer newCustomer) {
        int newId = -1;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryRegister)) {
            stmt.setString(1, newCustomer.getName());
            stmt.setString(2, newCustomer.getLastName());
            stmt.setString(3, newCustomer.getCity());
            stmt.setString(4, newCustomer.getAddress());
            stmt.setString(5, newCustomer.getPhone());
            stmt.setString(6, newCustomer.getEmail());
            stmt.setString(7, newCustomer.getPassword());

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    newId = rs.getInt(1);
                }
            }

        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return newId;
    }

    /**
     * Login user
     * @param emailUser user's email
     * @param passwordUser user's password
     * @return current user
     */
    @Override
    public Customer get(String emailUser, String passwordUser) {
        Customer loginUser = null;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryLogin)) {
            stmt.setString(1, emailUser);
            stmt.setString(2, passwordUser);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(COLUMN_ID);
                    String name = rs.getString(COLUMN_NAME);
                    String lastName = rs.getString(COLUMN_LAST_NAME);
                    String city = rs.getString(COLUMN_CITY);
                    String address = rs.getString(COLUMN_ADDRESS);
                    String phone = rs.getString(COLUMN_PHONE);
                    String email = rs.getString(COLUMN_EMAIL);
                    int idRole = rs.getInt(COLUMN_ROLE);
                    Role role = Role.values()[idRole];

                    loginUser = new Customer(id, name, lastName, city, address, phone, email, role);
                }
            }
        }catch(SQLException ex){
            log.debug(ex.getMessage());
        }
        return loginUser;
    }

    /**
     * Get user id
     * @param emailUser user's email
     * @param passwordUser user's password
     * @return current user
     */
    @Override
    public int getId(String emailUser, String passwordUser) {
        int customerId = 0;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryGetId)) {
            stmt.setString(1, emailUser);
            stmt.setString(2, passwordUser);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    customerId = rs.getInt(COLUMN_ID);
                }
            }
        }catch(SQLException ex){
            log.debug(ex.getMessage());
        }
        return customerId;
    }
}
