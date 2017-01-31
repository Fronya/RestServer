package com.fronya.auth;


import java.util.HashMap;
import java.util.Map;

public class UserAuthenticator extends Authenticator {
    private static UserAuthenticator authenticator;

    private Map<Integer, String> userTokensStorage = new HashMap<>();

    public static UserAuthenticator getInstance(){
        if(authenticator == null){
            authenticator = new UserAuthenticator();
        }
        return authenticator;
    }

    @Override
    public String login(int id) {
        return login(id, userTokensStorage);
    }

    @Override
    public boolean isAuthTokenValid(int id, String token) {
        return isAuthTokenValid(id, token, userTokensStorage);
    }

    @Override
    public boolean logout(int id, String token) {
        return logout(id, token, userTokensStorage);
    }
}
