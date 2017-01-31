package com.fronya.rest.size;

import com.fronya.auth.AdminSecure;
import com.fronya.dao.factory.FactoryDao;
import com.fronya.dao.size.SizeDao;
import com.fronya.model.size.Size;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/size")
public class SizeService {
    private Logger log = Logger.getLogger(SizeService.class);
    private SizeDao sizeDao = FactoryDao.getFactoryDao(FactoryDao.MYSQL).getSizeDao();

    @GET
    public Response getAllSizes(){
        List<Size> sizes = sizeDao.getAll();
        if(sizes.isEmpty()){
            log.debug("array of sizes is empty");
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }else {
            String output = new Gson().toJson(sizes);
            return Response.status(Response.Status.OK).entity(output).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSize(@PathParam("id") int id){
        Size findSize = sizeDao.get(id);
        if(findSize == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }else {
            return Response.status(Response.Status.OK).entity(findSize).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateSize(@PathParam("id") int id,
                               Size updateSize){
         if(sizeDao.update(updateSize)){
             return Response.status(Response.Status.OK).entity("true").build();
         }else {
             return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
         }
    }

    @AdminSecure
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSize(Size newSize){
        if(sizeDao.create(newSize)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }

    @AdminSecure
    @DELETE
    @Path("{id}")
    public Response updateSize(@PathParam("id") int id){
        if(sizeDao.delete(id)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("false").build();
        }
    }
}
