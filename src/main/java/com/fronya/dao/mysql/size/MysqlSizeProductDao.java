package com.fronya.dao.mysql.size;


import com.fronya.dao.mysql.connection.ConnectionPool;
import com.fronya.dao.size.SizeProductDao;
import com.fronya.model.size.SizeProduct;
import com.fronya.soap.client.PriceServer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MysqlSizeProductDao implements SizeProductDao{
    private static final Logger log = Logger.getLogger(MysqlSizeProductDao.class);

    private static final String PATH_FILE = "database";
    private static final String COLUMN_SIZE = "id_size";
    private static final String COLUMN_PRODUCT = "id_product";
    private static final String COLUMN_PRICE = "id_price";

    private static final String KEY_CREATE= "size_product.create";
    private static final String KEY_DELETE= "size_product.delete";
    private static final String KEY_GET_PRICE= "size_product.id_price";

    private static String queryCreate;
    private static String queryGetPrice;
    private static String queryDelete;

    static{
        ResourceBundle resource = ResourceBundle.getBundle(PATH_FILE);
        queryGetPrice = resource.getString(KEY_GET_PRICE);
        queryCreate = resource.getString(KEY_CREATE);
        queryDelete = resource.getString(KEY_DELETE);
    }


    @Override
    public boolean create(SizeProduct[] sizes, int idProduct) {
        boolean isCreate = false;

        Connection con = ConnectionPool.getConnection();
        for (SizeProduct size: sizes) {
            int idPrice = PriceServer.createPrice(size.getPrice());

            if(idPrice == -1){
                log.debug("Error create price");
                continue;
            }

            try(PreparedStatement stmt = con.prepareStatement(queryCreate)){
                stmt.setInt(1, idProduct);
                stmt.setString(2, size.getValue());
                stmt.setInt(3, idPrice);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        isCreate = true;
                    }
                }
            }catch (SQLException ex){
                log.debug(ex.getMessage());
            }
        }

        return isCreate;
    }

    @Override
    public boolean update(SizeProduct[] sizes, int idProduct) {
        if(!deletePrice(idProduct)){
            return false;
        }
        if(!delete(idProduct)){
            return false;
        }
        return create(sizes, idProduct);
    }

    private boolean delete(int idProduct){
        boolean isDelete = false;
        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryDelete)){
            stmt.setInt(1, idProduct);

            if(stmt.executeUpdate() > 0){
                isDelete = true;
            }
        } catch (SQLException ex) {
            log.debug(ex.getMessage());
        }
        return isDelete;
    }

    private boolean deletePrice(int idProduct){
        boolean isDelete = false;
        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryGetPrice)) {
            stmt.setInt(1, idProduct);

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int idPrice = rs.getInt(1);
                    if(PriceServer.deletePrice(idPrice)){
                        isDelete = true;
                    }
                }
            }
        }catch (SQLException ex) {
            log.debug(ex.getMessage());
        }
        return isDelete;
    }
}
