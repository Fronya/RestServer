package com.fronya.auth;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public abstract class Authenticator {

    private SecureRandom random = new SecureRandom();

    public abstract String login(int id);
    public abstract boolean isAuthTokenValid(int id, String token);
    public abstract boolean logout(int id, String token);

    private String getToken(){
        return new BigInteger(130, random).toString(32);
    }

    protected String login(int idUser, Map<Integer, String> tokensStorage){
        String token = getToken();
        tokensStorage.put(idUser, token);
        return token;
    }

    protected boolean isAuthTokenValid(int id, String token, Map<Integer, String> tokensStorage){
        boolean isValid = false;
        if(tokensStorage.containsKey(id)){
            if(tokensStorage.get(id).equals(token)){
                isValid = true;
            }
        }
        return isValid;
    }

    protected boolean logout(int id, String token, Map<Integer, String> tokensStorage){
        boolean isLogout = false;
        if(tokensStorage.containsKey(id)){
            tokensStorage.remove(id, token);
            isLogout = true;
        }
        return isLogout;
    }
}
