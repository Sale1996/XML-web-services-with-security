package com.tim9.agentapp.utils;

import org.jboss.logging.Logger;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

public class HeaderClientInterceptor implements ClientInterceptor {

		  @Override
		  public void afterCompletion(MessageContext arg0, Exception arg1)
		      throws WebServiceClientException {
		    // No-op
		  }

		  @Override
		  public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		    // No-op
		    return true;
		  }

		  @Override
		  public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {

		    return true;
		  }

		  @Override
		  public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {

		    return true;
		  }
		}
