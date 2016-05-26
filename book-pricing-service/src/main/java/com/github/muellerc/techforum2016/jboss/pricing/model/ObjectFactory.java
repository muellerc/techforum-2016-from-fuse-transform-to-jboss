
package com.github.muellerc.techforum2016.jboss.pricing.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.github.muellerc.techforum2016.jboss.pricing.model package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.github.muellerc.techforum2016.jboss.pricing.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookPriceRequest }
     * 
     */
    public GetBookPriceRequest createGetBookPriceRequest() {
        return new GetBookPriceRequest();
    }

    /**
     * Create an instance of {@link GetBookPriceResponse }
     * 
     */
    public GetBookPriceResponse createGetBookPriceResponse() {
        return new GetBookPriceResponse();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

}
