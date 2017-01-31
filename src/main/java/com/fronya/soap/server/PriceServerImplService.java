
package com.fronya.soap.server;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PriceServerImplService", targetNamespace = "http://server.soap.fronya.com/", wsdlLocation = "http://127.0.0.1:8080/price?wsdl")
public class PriceServerImplService
    extends Service
{

    private final static URL PRICESERVERIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException PRICESERVERIMPLSERVICE_EXCEPTION;
    private final static QName PRICESERVERIMPLSERVICE_QNAME = new QName("http://server.soap.fronya.com/", "PriceServerImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8080/price?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PRICESERVERIMPLSERVICE_WSDL_LOCATION = url;
        PRICESERVERIMPLSERVICE_EXCEPTION = e;
    }

    public PriceServerImplService() {
        super(__getWsdlLocation(), PRICESERVERIMPLSERVICE_QNAME);
    }

    public PriceServerImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PRICESERVERIMPLSERVICE_QNAME, features);
    }

    public PriceServerImplService(URL wsdlLocation) {
        super(wsdlLocation, PRICESERVERIMPLSERVICE_QNAME);
    }

    public PriceServerImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PRICESERVERIMPLSERVICE_QNAME, features);
    }

    public PriceServerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PriceServerImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PriceServerImpl
     */
    @WebEndpoint(name = "PriceServerImplPort")
    public PriceServerImpl getPriceServerImplPort() {
        return super.getPort(new QName("http://server.soap.fronya.com/", "PriceServerImplPort"), PriceServerImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PriceServerImpl
     */
    @WebEndpoint(name = "PriceServerImplPort")
    public PriceServerImpl getPriceServerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.soap.fronya.com/", "PriceServerImplPort"), PriceServerImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PRICESERVERIMPLSERVICE_EXCEPTION!= null) {
            throw PRICESERVERIMPLSERVICE_EXCEPTION;
        }
        return PRICESERVERIMPLSERVICE_WSDL_LOCATION;
    }

}