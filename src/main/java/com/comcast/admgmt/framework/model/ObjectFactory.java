
package com.comcast.admgmt.framework.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.comcast.admgmt.framework.model package. 
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

    private final static QName _RequestHeader_QNAME = new QName("http://framework.admgmt.comcast.com/model", "RequestHeader");
    private final static QName _ResponseHeader_QNAME = new QName("http://framework.admgmt.comcast.com/model", "ResponseHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.comcast.admgmt.framework.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link RequestHeaderType }
     * 
     */
    public RequestHeaderType createRequestHeaderType() {
        return new RequestHeaderType();
    }

    /**
     * Create an instance of {@link ResponseHeaderType }
     * 
     */
    public ResponseHeaderType createResponseHeaderType() {
        return new ResponseHeaderType();
    }

    /**
     * Create an instance of {@link GetAdListRequest }
     * 
     */
    public GetAdListRequest createGetAdListRequest() {
        return new GetAdListRequest();
    }

    /**
     * Create an instance of {@link GetAdListResponse }
     * 
     */
    public GetAdListResponse createGetAdListResponse() {
        return new GetAdListResponse();
    }

    /**
     * Create an instance of {@link AdList }
     * 
     */
    public AdList createAdList() {
        return new AdList();
    }

    /**
     * Create an instance of {@link AdInfo }
     * 
     */
    public AdInfo createAdInfo() {
        return new AdInfo();
    }

    /**
     * Create an instance of {@link CreateAdRequest }
     * 
     */
    public CreateAdRequest createCreateAdRequest() {
        return new CreateAdRequest();
    }

    /**
     * Create an instance of {@link CreateAdResponse }
     * 
     */
    public CreateAdResponse createCreateAdResponse() {
        return new CreateAdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://framework.admgmt.comcast.com/model", name = "RequestHeader")
    public JAXBElement<RequestHeaderType> createRequestHeader(RequestHeaderType value) {
        return new JAXBElement<RequestHeaderType>(_RequestHeader_QNAME, RequestHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://framework.admgmt.comcast.com/model", name = "ResponseHeader")
    public JAXBElement<ResponseHeaderType> createResponseHeader(ResponseHeaderType value) {
        return new JAXBElement<ResponseHeaderType>(_ResponseHeader_QNAME, ResponseHeaderType.class, null, value);
    }

}
