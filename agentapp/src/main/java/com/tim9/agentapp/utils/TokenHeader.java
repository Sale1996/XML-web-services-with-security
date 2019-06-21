package com.tim9.agentapp.utils;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

import com.tim9.agentapp.user.model.Bearer;

public class TokenHeader implements WebServiceMessageCallback {

	    private Bearer authentication;

	    public TokenHeader(Bearer authentication) {
	        this.authentication = authentication;
	    }

	    @Override
	    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
	        SoapHeader soapHeader = ((SoapMessage)message).getSoapHeader();

	        try {
	            JAXBContext context = JAXBContext.newInstance(Bearer.class);

	            Marshaller marshaller = context.createMarshaller();
	            marshaller.marshal(authentication, soapHeader.getResult());

	        } catch (JAXBException e) {
	            throw new IOException("error while marshalling authentication.");
	        }
	    }
	}
