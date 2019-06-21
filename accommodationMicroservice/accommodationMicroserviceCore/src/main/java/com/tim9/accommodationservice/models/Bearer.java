package com.tim9.accommodationservice.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Bearer", namespace = Bearer.AUTH_NS)
public class Bearer {

	public static final String AUTH_NS = "http://tim9.com/security";
	
	@XmlElement(name = "token", namespace = AUTH_NS)
    private String token;

    public Bearer() {
    }

	public Bearer(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
