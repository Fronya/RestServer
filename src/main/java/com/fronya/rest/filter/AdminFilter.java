package com.fronya.rest.filter;


import com.fronya.auth.AdminAuthenticator;
import com.fronya.auth.AdminSecure;
import com.fronya.auth.HttpHeadersNames;
import org.apache.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@AdminSecure
public class AdminFilter implements ContainerRequestFilter {
    Logger log = Logger.getLogger(AdminFilter.class);

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
            AdminAuthenticator authenticator = AdminAuthenticator.getInstance();
            if(!authenticator.isAuthTokenValid(-idUser, token)){
                log.debug("User is bad");
                throw new AuthenticationException();
            }
            log.debug("User is good");
        }catch(AuthenticationException ex){
            containerRequestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User isn't admin")
                    .build());
        }
    }
}
