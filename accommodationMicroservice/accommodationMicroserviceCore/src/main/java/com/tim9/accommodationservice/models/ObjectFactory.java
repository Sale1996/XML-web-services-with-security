//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import javax.xml.bind.annotation.XmlRegistry;




/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lukacvetinovic.backend_tim_9 package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lukacvetinovic.backend_tim_9
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccommodationUnit }
     * 
     */
    
    
    public GetAccommodationUnitsRequest createGetAccommodationUnitsRequest() {
        return new GetAccommodationUnitsRequest();
    }

    public GetAccommodationUnitsResponse createGetAccommodationUnitsResponse() {
        return new GetAccommodationUnitsResponse();
    }
    
    
    public AccommodationUnit createAccommodationUnit() {
        return new AccommodationUnit();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    
    public GetCategoriesRequest createGetCategoriesRequest() {
        return new GetCategoriesRequest();
    }

    public GetCategoriesResponse createGetCategoriesResponse() {
        return new GetCategoriesResponse();
    }
    
    public Category createCategory() {
        return new Category();
    }
    
    
    /**
     * 
     * Create an instance of {@link City }
     */
    public GetCitiesRequest createGetCitiesRequest() {
        return new GetCitiesRequest();
    }

    public GetCitiesResponse createGetCitiesResponse() {
        return new GetCitiesResponse();
    }
    
    public City createCity() {
        return new City();
    }

    /**
     * Create an instance of {@link Type }
     * 
     */
    public Type createType() {
        return new Type();
    }

    /**
     * Create an instance of {@link ExtraField }
     * 
     */
    
    public GetExtraFieldsRequest createGetExtraFieldsRequest() {
        return new GetExtraFieldsRequest();
    }

    public GetExtraFieldsResponse createGetExtraFieldsResponse() {
        return new GetExtraFieldsResponse();
    }
    
    public ExtraField createExtraField() {
        return new ExtraField();
    }

    /**
     * Create an instance of {@link Price }
     * 
     */
    public Price createPrice() {
        return new Price();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    
    public GetCommentsRequest createGetCommentsRequest() {
        return new GetCommentsRequest();
    }

    public GetCommentsResponse createGetCommentsResponse() {
        return new GetCommentsResponse();
    }
    
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link Accommodation }
     * 
     */
    
    public GetAccommodationRequest createGetAccommodationRequest() {
        return new GetAccommodationRequest();
    }

    public GetAccommodationResponse createGetAccommodationResponse() {
        return new GetAccommodationResponse();
    }
    
    public Accommodation createAccommodation() {
        return new Accommodation();
    }

    /**
     * Create an instance of {@link Picture }
     * 
     */
    public Picture createPicture() {
        return new Picture();
    }

}
