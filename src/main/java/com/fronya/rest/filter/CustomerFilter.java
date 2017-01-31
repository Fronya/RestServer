package com.fronya.rest.filter;


import com.fronya.auth.CustomerSecure;
import com.fronya.auth.HttpHeadersNames;
import com.fronya.auth.UserAuthenticator;
import org.apache.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@CustomerSecure
public class CustomerFilter implements ContainerRequestFilter{
    Logger log = Logger.getLogger(CustomerFilter.class);

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        try{
            int idUser;
            try {
                idUser = Integer.parseInt(containerRequestContext.getHeaderString(HttpHeadersNames.ID_USER));
            }catch (NumberFormatException ex){
                throw new AuthenticationException();
            }
            String token = containerRequestContext.getHeaderString(HttpHeadersNames.AUTH_TOKEN);

            log.debug("idUser = " + idUser + ", token = " + token);
            UserAuthenticator authenticator = UserAuthenticator.getInstance();
            if(!authenticator.isAuthTokenValid(idUser, token)){
                log.debug("User is bad");
                throw new AuthenticationException();
            }log.debug("User is good");
        }catch(AuthenticationException ex){
            containerRequestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User isn't admin")
                    .build());
        }
    }
}
