package com.fronya.rest.product;

import com.fronya.auth.AdminSecure;
import com.fronya.dao.factory.FactoryDao;
import com.fronya.dao.product.ProductDao;
import com.fronya.dao.size.SizeProductDao;
import com.fronya.model.product.ProductFull;
import com.fronya.model.product.ProductShort;
import com.fronya.model.size.SizeProduct;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductService {
    private Logger log = Logger.getLogger(ProductService.class);
    private ProductDao productDao = FactoryDao.getFactoryDao(FactoryDao.MYSQL).getProductDao();

    @GET
    public Response getProducts(
            @DefaultValue("1") @QueryParam("page") int pageNum,
            @DefaultValue("2") @QueryParam("type") int type){
        List<ProductShort> productShortList = productDao.getAll(pageNum, type);
        String output = new Gson().toJson(productShortList);
        return Response.status(Response.Status.OK).entity(output).build();
    }

    @GET
    @Path("{id}")
    public Response getProduct(@PathParam("id") int id){
        ProductFull product = productDao.get(id);
        String output = new Gson().toJson(product);
        return Response.status(Response.Status.OK).entity(output).build();
    }

    @POST
    @AdminSecure
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductFull newProduct){
        int newId = productDao.create(newProduct);
        if(newId != -1){
            return Response.status(Response.Status.OK).entity("{\"id\":\"" + newId + "\"}").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }

    @PUT
    @AdminSecure
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(
            @PathParam("id") int id,
            ProductFull updateProduct) {
        if(productDao.update(updateProduct)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }

    @DELETE
    @AdminSecure
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int id){
        if(productDao.delete(id)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }

    private SizeProductDao sizeProductDao = FactoryDao.getFactoryDao(FactoryDao.MYSQL).getSizeProductDao();

    @POST
    @Path("{id}/sizes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSizeProduct(@PathParam("id") int idProduct,
                                      SizeProduct[] sizes){
        if(sizeProductDao.create(sizes, idProduct)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }

    @PUT
    @Path("{id}/sizes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSizeProduct(@PathParam("id") int idProduct,
                                      SizeProduct[] sizes){
        if(sizeProductDao.update(sizes, idProduct)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }
}
