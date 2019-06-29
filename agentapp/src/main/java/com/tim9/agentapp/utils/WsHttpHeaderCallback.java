package com.tim9.agentapp.utils;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.util.StringUtils;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.transport.HeadersAwareSenderWebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;

public class WsHttpHeaderCallback implements WebServiceMessageCallback
{
    private String headerValue;

    public WsHttpHeaderCallback(String headerValue)
    {
        super();
        this.headerValue = headerValue;
        validateRequiredFields();
    }

    public WsHttpHeaderCallback()
    {
        super();
    }

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException
    {
        validateRequiredFields();
        addRequestHeader(headerValue);      
    }
    private void validateRequiredFields()
    {
        if( !StringUtils.hasText(headerValue) )
        {
            throw new IllegalArgumentException("Token is not present: ["+headerValue+"]");
        }       
    }
    private void addRequestHeader(String headerValue) throws IOException
    {
        TransportContext context = TransportContextHolder.getTransportContext();
        HeadersAwareSenderWebServiceConnection connection = (HeadersAwareSenderWebServiceConnection) context.getConnection();
        connection.addRequestHeader("Authorization", "Bearer " + headerValue);
    }   
}
