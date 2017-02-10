package com.fronya.rest.customer;

import com.fronya.auth.AdminSecure;
import com.fronya.auth.CustomerSecure;
import com.fronya.auth.HttpHeadersNames;
import com.fronya.dao.customer.CustomerDao;
import com.fronya.dao.factory.FactoryDao;
import com.fronya.model.customer.Customer;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserService {
    private Logger log = Logger.getLogger(UserService.class);
    private CustomerDao customerDao = FactoryDao.getFactoryDao(FactoryDao.MYSQL).getCustomerDao();

    @GET
    @CustomerSecure
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoCustomer(@HeaderParam(HttpHeadersNames.ID_USER) String idUserString,
                                    @PathParam("id") int id){
        int idUser;
        try{
            idUser = Integer.parseInt(idUserString);
        }catch (NumberFormatException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity("headers are bad").build();
        }

        if(id != idUser){
            Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return getCustomer(id);
    }

    private Response getCustomer(int id){
        Customer findCustomer = customerDao.get(id, true);
        log.debug("find customer: " + findCustomer);
        if(findCustomer == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.OK).entity(findCustomer).build();
        }
    }

    @PUT
    @CustomerSecure
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Customer customer, @PathParam("id") int id){
        if(id != customer.getId()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean isUpdate = customerDao.update(customer);
        if(isUpdate){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.OK).entity("false").build();
        }
    }
}
