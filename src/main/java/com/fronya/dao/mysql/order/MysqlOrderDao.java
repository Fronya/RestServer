package com.fronya.dao.mysql.order;


import com.fronya.dao.mysql.connection.ConnectionPool;
import com.fronya.dao.order.OrderDao;
import com.fronya.model.order.Order;
import com.fronya.model.order.OrderAdmin;
import com.fronya.model.order.OrderAdminShort;
import com.fronya.model.order.OrderList;
import com.fronya.soap.client.PriceServer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MysqlOrderDao implements OrderDao{
    private static final Logger log = Logger.getLogger(MysqlOrderDao.class);

    private static final String PATH_FILE = "database";
    private static final String COLUMN_ORDER = "id_order";
    private static final String COLUMN_CUSTOMER = "id_customer";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_COUNT = "count";
    private static final String COLUMN_ID_PRICE = "id_price";
    private static final String COLUMN_VALUE = "value";
    private static final String COLUMN_NAME = "name";

    private static final String KEY_CREATE_MAIN = "order.create_main";
    private static final String KEY_CREATE = "order.create";
    private static final String KEY_GET_ALL = "order.get_all";
    private static final String KEY_DELETE = "order.delete";

    private static String queryCreateMain;
    private static String queryCreate;
    private static String queryGetAll;
    private static String queryDelete;

    static{
        ResourceBundle resource = ResourceBundle.getBundle(PATH_FILE);
        queryCreateMain = resource.getString(KEY_CREATE_MAIN);
        queryCreate = resource.getString(KEY_CREATE);
        queryGetAll = resource.getString(KEY_GET_ALL);
        queryDelete = resource.getString(KEY_DELETE);
    }

    @Override
    public boolean createOrder(OrderList list, int idUser) {
        boolean isCreate = false;
        int orderId = -1;

        Connection con = ConnectionPool.getConnection();
        try {
            con.setAutoCommit(false);
            try(PreparedStatement stmt = con.prepareStatement(queryCreateMain)) {
                stmt.setInt(1, idUser);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        orderId = rs.getInt(1);
                    }
                }
            }

            if(orderId == -1){
                return isCreate;
            }

            try(PreparedStatement stmt = con.prepareStatement(queryCreate)){
                for (Order o: list.getOrders()) {
                    stmt.setInt(1, orderId);
                    stmt.setInt(2, o.getCount());
                    stmt.setInt(3, o.getIdSizeProduct());

                    if(stmt.executeUpdate() == 0){
                        throw new SQLException();
                    }
                }
            }
            isCreate = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException exBack) {
                exBack.printStackTrace();
            }
            e.printStackTrace();
            log.debug(e.getMessage());
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isCreate;
    }

    @Override
    public List<OrderAdmin> getAllOrders() {
        List<OrderAdmin> list = new ArrayList<>();
        Connection con = ConnectionPool.getConnection();
        OrderAdmin currentList = null;
        try(PreparedStatement stmt = con.prepareStatement(queryGetAll);
            ResultSet rs = stmt.executeQuery()){
            int idCurrentOrder = -1;
            while(rs.next()){
                int idOrder = rs.getInt(COLUMN_ORDER);
                log.debug("id order = " + idOrder);
                if(idCurrentOrder != idOrder){
                    if(currentList != null){
                        list.add(currentList);
                    }

                    idCurrentOrder = idOrder;
                    int idCustomer = rs.getInt(COLUMN_CUSTOMER);
                    Date date = rs.getDate(COLUMN_DATE);
                    Time time = rs.getTime(COLUMN_TIME);
                    currentList = new OrderAdmin(idCustomer, idOrder, date, time);
                }

                int count = rs.getInt(COLUMN_COUNT);
                String name = rs.getString(COLUMN_NAME);
                String value = rs.getString(COLUMN_VALUE);
                int idPrice = rs.getInt(COLUMN_ID_PRICE);

                double price = PriceServer.getPrice(idPrice);
                currentList.addOrder(new OrderAdminShort(count, price, value, name));
            }
            list.add(currentList);
        }catch (SQLException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteOrder(int idOrder) {
        boolean isDelete = false;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryDelete)){
            stmt.setInt(1, idOrder);

            if(stmt.executeUpdate() > 0){
                isDelete = true;
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return isDelete;
    }


}
