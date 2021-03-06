//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.01 at 10:28:48 PM CEST 
//


package com.tim9.userservice.models;

import javax.xml.bind.annotation.XmlRegistry;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.spring.guides.gs_producing_web_service package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.spring.guides.gs_producing_web_service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCountryRequest }
     * 
     */
    public GetAgentRequest createGetAgentRequest() {
        return new GetAgentRequest();
    }

    /**
     * Create an instance of {@link GetCountryResponse }
     * 
     */
    public GetAgentResponse createGetAgentResponse() {
        return new GetAgentResponse();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Agent createAgent() {
        return new Agent();
    }
    
    public GetMessagesRequest createGetMessagesRequest() {
        return new GetMessagesRequest();
    }

    public GetMessagesResponse createGetMessagesResponse() {
        return new GetMessagesResponse();
    }

    public Message createMessage() {
        return new Message();
    }

    public CreateMessageRequest createCreateMessageRequest() {
        return new CreateMessageRequest();
    }

    public CreateMessageResponse createCreateMessageResponse() {
        return new CreateMessageResponse();
    }
    
    public UpdateMessageRequest createUpdateMessageRequest() {
        return new UpdateMessageRequest();
    }

    public UpdateMessageResponse createUpdateMessageResponse() {
        return new UpdateMessageResponse();
    }
    
    public UpdateAgentRequest createUpdateAgentRequest() {
        return new UpdateAgentRequest();
    }

    public UpdateAgentResponse createUpdateAgentResponse() {
        return new UpdateAgentResponse();
    }
    
    public UpdateAgentPasswordRequest updateAgentPasswordRequest() {
        return new UpdateAgentPasswordRequest();
    }

    public UpdateAgentPasswordResponse updateAgentPasswordResponse() {
        return new UpdateAgentPasswordResponse();
    }

    public GetUsersRequest createGetUsersRequest() {
        return new GetUsersRequest();
    }

    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }
    
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }
    
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }
}
