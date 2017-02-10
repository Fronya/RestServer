package com.fronya.dao.mysql.product;


import com.fronya.dao.mysql.connection.ConnectionPool;
import com.fronya.dao.product.ProductDao;
import com.fronya.model.product.ProductFull;
import com.fronya.model.product.ProductShort;
import com.fronya.model.size.SizeProduct;
import com.fronya.soap.client.PriceServer;
import org.apache.log4j.Logger;

import javax.xml.soap.SOAPException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MysqlProductDao implements ProductDao {
    private static final Logger log = Logger.getLogger(MysqlProductDao.class);

    private static final String PATH_FILE = "database";
    private static final String COLUMN_ID = "id_product";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image_path";
    private static final String COLUMN_PRICE = "id_price";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_SIZE = "id_size_product";
    private static final String COLUMN_SIZE_NAME = "name";
    private static final String COLUMN_TYPE= "id_type";

    private static final String KEY_GET_ALL= "product.get_all";
    private static final String KEY_GET= "product.get";
    private static final String KEY_GET_SIZES= "product.get_sizes";
    private static final String KEY_CREATE= "product.create";
    private static final String KEY_UPDATE= "product.update";
    private static final String KEY_DELETE= "product.delete";

    private static String queryGetAll;
    private static String queryGet;
    private static String queryGetSizes;
    private static String queryCreate;
    private static String queryUpdate;
    private static String queryDelete;

    static {
        ResourceBundle resource = ResourceBundle.getBundle(PATH_FILE);
        queryGetAll = resource.getString(KEY_GET_ALL);
        queryGet = resource.getString(KEY_GET);
        queryGetSizes = resource.getString(KEY_GET_SIZES);
        queryCreate = resource.getString(KEY_CREATE);
        queryUpdate = resource.getString(KEY_UPDATE);
        queryDelete = resource.getString(KEY_DELETE);
    }


    @Override
    public int create(ProductFull newProduct) {
        int newId = -1;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryCreate)){
            stmt.setString(1, newProduct.getDescription());
            stmt.setInt(2, newProduct.getIdType());
            stmt.setString(3, newProduct.getPathImage());
            stmt.setString(4, newProduct.getName());

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

    @Override
    public boolean update(ProductFull updateProduct) {
        boolean isUpdate = false;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryUpdate)) {
            stmt.setInt(1, updateProduct.getIdType());
            stmt.setString(2, updateProduct.getDescription());
            stmt.setString(3, updateProduct.getPathImage());
            stmt.setString(4, updateProduct.getName());
            stmt.setInt(5, updateProduct.getId());

            if(stmt.executeUpdate() > 0){
                isUpdate = true;
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }

        return isUpdate;
    }

    @Override
    public boolean delete(int idProduct) {
        boolean isDelete = false;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryDelete)) {
            stmt.setInt(1, idProduct);

            if(stmt.executeUpdate() > 0){
                isDelete = true;
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return isDelete;
    }

    /**
     * Get all inform about product
     * @param id id product
     * @return all about product
     */
    @Override
    public ProductFull get(int id) {
        ProductFull findProduct = null;

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryGet)){
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    String name = rs.getString(COLUMN_NAME);
                    String pathImage = rs.getString(COLUMN_IMAGE);
                    String description = rs.getString(COLUMN_DESCRIPTION);
                    int idType = rs.getInt(COLUMN_TYPE);

                    findProduct = new ProductFull(id, name, pathImage, description, idType);
                }
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }

        //get sizes
        if(findProduct != null){
            List<SizeProduct> sizeList = new ArrayList<>();
            try(PreparedStatement stmt = con.prepareStatement(queryGetSizes)){
                stmt.setInt(1, id);

                try(ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        try {
                            int idSize = rs.getInt(COLUMN_SIZE);
                            String name = rs.getString(COLUMN_SIZE_NAME);
                            int idPrice = rs.getInt(COLUMN_PRICE);

                            double price = PriceServer.getPrice(idPrice);
                            if (price == -1) {
                                throw new SOAPException("id = " + idPrice);
                            }
                            sizeList.add(new SizeProduct(idSize, name, price));
                        }catch (SOAPException ex) {
                            log.debug("SOAP doesn't get price: " + ex.getMessage());
                        }
                    }
                }
                findProduct.setSizes(sizeList);
            }catch (SQLException ex){
                log.debug(ex.getMessage());
            }
        }
        return findProduct;
    }

    /**
     * Get list of product
     * @param pageNum
     * @param typePerfume
     * @return
     */
    @Override
    public List<ProductShort> getAll(int pageNum, int typePerfume) {
        List<ProductShort> productShortList = new ArrayList<>();

        Connection con = ConnectionPool.getConnection();
        try(PreparedStatement stmt = con.prepareStatement(queryGetAll)) {
            stmt.setInt(1, typePerfume);

            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    try {
                        int id = rs.getInt(COLUMN_ID);
                        String name = rs.getString(COLUMN_NAME);
                        String image = rs.getString(COLUMN_IMAGE);
                        int idPrice = rs.getInt(COLUMN_PRICE);

                        double price = PriceServer.getPrice(idPrice);
                        if (price == -1) {
                            throw new SOAPException("id = " + idPrice);
                        }
                        productShortList.add(new ProductShort(id, name, image, price));
                    }catch (SOAPException ex){
                        log.debug("SOAP doesn't get price: " + ex.getMessage());
                    }
                }
            }
        }catch (SQLException ex){
            log.debug(ex.getMessage());
        }
        return productShortList;
    }
}
