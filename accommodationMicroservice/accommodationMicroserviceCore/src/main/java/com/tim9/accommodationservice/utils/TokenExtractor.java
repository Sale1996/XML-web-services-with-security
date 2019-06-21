package com.tim9.accommodationservice.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.ws.soap.SoapHeaderElement;

import com.tim9.accommodationservice.models.Bearer;

public class TokenExtractor {
	
	public static String getBearerToken(SoapHeaderElement header){
		Bearer bearerToken = null;
		String authorization = "";
        try {

            JAXBContext context = JAXBContext.newInstance(Bearer.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bearerToken = (Bearer) unmarshaller.unmarshal(header.getSource());
            authorization = bearerToken.getToken();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return authorization;
    }
}
