package com.fronya.soap.client;


import com.fronya.soap.server.PriceServerImpl;
import com.fronya.soap.server.PriceServerImplService;

public class PriceServer {
    private static PriceServerImpl service;

    private PriceServer(){}

    private static PriceServerImpl getInstance(){
        if(service == null){
            service = (new PriceServerImplService()).getPriceServerImplPort();
        }
        return service;
    }

    public static int createPrice(double price){
        return getInstance().createPrice(price);
    }

    public static boolean deletePrice(int id){
        return getInstance().deletePrice(id);
    }

    public static boolean updatePrice(int id, double newPrice){
        return getInstance().updatePrice(id, newPrice);
    }

    public static double getPrice(int id){
        return getInstance().getPrice(id);
    }

}
