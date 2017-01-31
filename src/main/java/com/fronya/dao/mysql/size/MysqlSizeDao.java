package com.fronya.dao.mysql.size;


import com.fronya.dao.mysql.connection.ConnectionPool;
import com.fronya.dao.size.SizeDao;
import com.fronya.model.size.Size;
import com.fronya.model.size.SizeProduct;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MysqlSizeDao implements SizeDao{
    private static final Logger log = Logger.getLogger(MysqlSizeDao.class);

    private static final String PATH_FILE = "database";
    private static final String COLUMN_ID = "id_size";
    private static final String COLUMN_NAME = "name";

    private static final String KEY_GET_ALL= "size.get_all";
    private static final String KEY_GET= "size.get";
    private static final String KEY_CREATE= "size.create";
    private static final String KEY_UPDATE= "size.update";
    private static final String KEY_DELETE= "size.delete";

    private static String queryGetAll;
    private static String queryGet;
    private static String queryCreate;
    private static String queryUpdate;
    private static String queryDelete;

    static {
        ResourceBundle resource = ResourceBundle.getBundle(PATH_FILE);
        queryGetAll = resource.getString(KEY_GET_ALL);
        queryGet = resource.getString(KEY_GET);
        queryCreate = resource.getString(KEY_CREATE);
        queryUpdate = resource.getString(KEY_UPDATE);
        queryDelete = resource.getString(KEY_DELETE);
    }


    @Override
    public boolean create(Size newSize) {
        boolean isCreate = false;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryCreate)){
            stmt.setString(1, newSize.getValue());

            if(stmt.executeUpdate() > 0){
                isCreate = true;
            }
        }catch(SQLException ex){
            log.debug(ex.getMessage());
        }
        return isCreate;
    }

    @Override
    public boolean update(Size updateSize) {
        boolean isUpdate = false;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryUpdate)) {
            stmt.setString(1, updateSize.getValue());
            stmt.setInt(2, updateSize.getId());

            if(stmt.executeUpdate() > 0){
                isUpdate = true;
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return isUpdate;
    }

    @Override
    public boolean delete(int idSize) {
        boolean isDelete = false;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryDelete)){
            stmt.setInt(1, idSize);

            if(stmt.executeUpdate() > 0){
                isDelete = true;
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return isDelete;
    }


    @Override
    public List<Size> getAll() {
        List<Size> products = new ArrayList<>();

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryGetAll);
            ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                int id = rs.getInt(COLUMN_ID);
                String name = rs.getString(COLUMN_NAME);
                products.add(new Size(id, name));
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return products;
    }

    @Override
    public Size get(int idSize) {
        Size findSize = null;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryGet)){
            stmt.setInt(1, idSize);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(COLUMN_ID);
                    String name = rs.getString(COLUMN_NAME);
                    findSize = new Size(id, name);
                }
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return findSize;
    }
}
