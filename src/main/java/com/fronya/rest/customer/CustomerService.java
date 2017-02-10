package com.fronya.rest.customer;

import com.fronya.auth.*;
import com.fronya.dao.customer.CustomerDao;
import com.fronya.dao.factory.FactoryDao;
import com.fronya.dao.order.OrderDao;
import com.fronya.model.customer.Customer;
import com.fronya.model.customer.LoginCustomer;
import com.fronya.model.customer.RegisterCustomer;
import com.fronya.model.order.OrderAdmin;
import com.fronya.model.order.OrderList;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/customers")
public class CustomerService {
    private Logger log = Logger.getLogger(CustomerService.class);
    private CustomerDao customerDao = FactoryDao.getFactoryDao(FactoryDao.MYSQL).getCustomerDao();

    @GET
    @AdminSecure
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id){
        return getCustomer(id);
    }

    private Response getCustomer(int id){
        Customer findCustomer = customerDao.get(id, false);
        log.debug("find customer: " + findCustomer);
        if(findCustomer == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.OK).entity(findCustomer).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginCustomer customerInfo){
        Customer currentCustomer = customerDao.get(customerInfo.getEmail(), customerInfo.getPassword());

        log.debug("current customer = " + currentCustomer);
        if(currentCustomer != null){
            Authenticator authenticator = null;
            int userId = 0;
            switch(currentCustomer.getRole()){
                case USER:
                    authenticator = UserAuthenticator.getInstance();
                    userId = currentCustomer.getId();
                    break;
                case ADMIN:
                    authenticator = AdminAuthenticator.getInstance();
                    userId = -currentCustomer.getId();
                    break;
            }

            String token = authenticator.login(currentCustomer.getId());

            String output = "{\"token\":\""+ token +"\",\"id\":\""+ userId + "\"}";
            return Response.status(Response.Status.OK).entity(output).build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegisterCustomer customerInfo) {
        int newId = customerDao.create(customerInfo);
        if(newId != -1){
            NewCookie userCookie = new NewCookie("userId", String.valueOf(newId));
            Authenticator authenticator = UserAuthenticator.getInstance();
            String token = authenticator.login(newId);

            String output = "{\"token\":\""+ token +"\",\"id\":\""+ newId +"\"}";
            return Response.status(Response.Status.OK).cookie(userCookie).entity(output).build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/logout")
    public Response logout(@HeaderParam(HttpHeadersNames.ID_USER) String idUserString,
                           @HeaderParam(HttpHeadersNames.AUTH_TOKEN) String token){
        int idUser;
        try{
            idUser = Integer.parseInt(idUserString);
        }catch (NumberFormatException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity("headers are bad").build();
        }

        Authenticator authenticator = AdminAuthenticator.getInstance();
        if(authenticator.logout(-idUser, token)){
            return Response.status(Response.Status.OK).entity("true").build();
        }

        authenticator = UserAuthenticator.getInstance();
        if(authenticator.logout(idUser, token)){
            return Response.status(Response.Status.OK).entity("true").build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity("not logout").build();
    }

    private OrderDao orderDao = FactoryDao.getFactoryDao(FactoryDao.MYSQL).getOrderDao();

    @POST
    @CustomerSecure
    @Path("{id}/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(@PathParam("id") int id,
                             OrderList list) {
        boolean isCreateOrder = orderDao.createOrder(list, id);
        if(isCreateOrder){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.OK).entity("false").build();
        }
    }

    @GET
    @AdminSecure
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(){
        List<OrderAdmin> list = orderDao.getAllOrders();
        if(list != null){
            return Response.status(Response.Status.OK).entity(list).build();
        }else{
            return Response.status(Response.Status.OK).entity("false").build();
        }
    }

    @DELETE
    @AdminSecure
    @Path("/orders/{id}")
    public Response deleteOrder(@PathParam("id") int id){
        if(orderDao.deleteOrder(id)){
            return Response.status(Response.Status.OK).entity("true").build();
        }else{
            return Response.status(Response.Status.OK).entity("false").build();
        }
    }
}
