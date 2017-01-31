
package com.fronya.soap.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fronya.soap.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreatePrice_QNAME = new QName("http://server.soap.fronya.com/", "createPrice");
    private final static QName _CreatePriceResponse_QNAME = new QName("http://server.soap.fronya.com/", "createPriceResponse");
    private final static QName _DeletePriceResponse_QNAME = new QName("http://server.soap.fronya.com/", "deletePriceResponse");
    private final static QName _UpdatePrice_QNAME = new QName("http://server.soap.fronya.com/", "updatePrice");
    private final static QName _GetPrice_QNAME = new QName("http://server.soap.fronya.com/", "getPrice");
    private final static QName _GetPriceResponse_QNAME = new QName("http://server.soap.fronya.com/", "getPriceResponse");
    private final static QName _DeletePrice_QNAME = new QName("http://server.soap.fronya.com/", "deletePrice");
    private final static QName _UpdatePriceResponse_QNAME = new QName("http://server.soap.fronya.com/", "updatePriceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fronya.soap.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeletePrice }
     * 
     */
    public DeletePrice createDeletePrice() {
        return new DeletePrice();
    }

    /**
     * Create an instance of {@link UpdatePriceResponse }
     * 
     */
    public UpdatePriceResponse createUpdatePriceResponse() {
        return new UpdatePriceResponse();
    }

    /**
     * Create an instance of {@link GetPriceResponse }
     * 
     */
    public GetPriceResponse createGetPriceResponse() {
        return new GetPriceResponse();
    }

    /**
     * Create an instance of {@link CreatePrice }
     * 
     */
    public CreatePrice createCreatePrice() {
        return new CreatePrice();
    }

    /**
     * Create an instance of {@link CreatePriceResponse }
     * 
     */
    public CreatePriceResponse createCreatePriceResponse() {
        return new CreatePriceResponse();
    }

    /**
     * Create an instance of {@link DeletePriceResponse }
     * 
     */
    public DeletePriceResponse createDeletePriceResponse() {
        return new DeletePriceResponse();
    }

    /**
     * Create an instance of {@link UpdatePrice }
     * 
     */
    public UpdatePrice createUpdatePrice() {
        return new UpdatePrice();
    }

    /**
     * Create an instance of {@link GetPrice }
     * 
     */
    public GetPrice createGetPrice() {
        return new GetPrice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "createPrice")
    public JAXBElement<CreatePrice> createCreatePrice(CreatePrice value) {
        return new JAXBElement<CreatePrice>(_CreatePrice_QNAME, CreatePrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "createPriceResponse")
    public JAXBElement<CreatePriceResponse> createCreatePriceResponse(CreatePriceResponse value) {
        return new JAXBElement<CreatePriceResponse>(_CreatePriceResponse_QNAME, CreatePriceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "deletePriceResponse")
    public JAXBElement<DeletePriceResponse> createDeletePriceResponse(DeletePriceResponse value) {
        return new JAXBElement<DeletePriceResponse>(_DeletePriceResponse_QNAME, DeletePriceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "updatePrice")
    public JAXBElement<UpdatePrice> createUpdatePrice(UpdatePrice value) {
        return new JAXBElement<UpdatePrice>(_UpdatePrice_QNAME, UpdatePrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "getPrice")
    public JAXBElement<GetPrice> createGetPrice(GetPrice value) {
        return new JAXBElement<GetPrice>(_GetPrice_QNAME, GetPrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "getPriceResponse")
    public JAXBElement<GetPriceResponse> createGetPriceResponse(GetPriceResponse value) {
        return new JAXBElement<GetPriceResponse>(_GetPriceResponse_QNAME, GetPriceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "deletePrice")
    public JAXBElement<DeletePrice> createDeletePrice(DeletePrice value) {
        return new JAXBElement<DeletePrice>(_DeletePrice_QNAME, DeletePrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.fronya.com/", name = "updatePriceResponse")
    public JAXBElement<UpdatePriceResponse> createUpdatePriceResponse(UpdatePriceResponse value) {
        return new JAXBElement<UpdatePriceResponse>(_UpdatePriceResponse_QNAME, UpdatePriceResponse.class, null, value);
    }

}
