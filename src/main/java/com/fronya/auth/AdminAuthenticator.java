package com.fronya.auth;


import java.util.HashMap;
import java.util.Map;

public class AdminAuthenticator extends Authenticator{
    private static AdminAuthenticator authenticator;

    private Map<Integer, String> adminTokensStorage = new HashMap<>();

    public static AdminAuthenticator getInstance(){
        if(authenticator == null){
            authenticator = new AdminAuthenticator();
        }
        return authenticator;
    }

    @Override
    public String login(int id) {
        return login(-id, adminTokensStorage);
    }

    @Override
    public boolean isAuthTokenValid(int id, String token) {
        return isAuthTokenValid(-id, token, adminTokensStorage);
    }

    @Override
    public boolean logout(int id, String token) {
        return logout(-id, token, adminTokensStorage);
    }
}
