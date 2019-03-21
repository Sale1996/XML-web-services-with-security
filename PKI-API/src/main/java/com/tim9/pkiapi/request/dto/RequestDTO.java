package com.tim9.pkiapi.request.dto;

import com.tim9.pkiapi.request.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

	private Long id;

	private String commonName;
	
	private String country;
	
	private String locality;
	
	private String state;
	
	private String organisation;
	
	private String organisationUnit;
	
	private String publicKey;
	
	private String email;

	public RequestDTO(Request request) {
		this.id = request.getId();
		this.commonName = request.getCommonName();
		this.country = request.getCountry();
		this.locality = request.getLocality();
		this.state = request.getState();
		this.organisation = request.getOrganisation();
		this.organisationUnit = request.getOrganisationUnit();
		this.publicKey = request.getPublicKey();
		this.email = request.getEmail();
	}
	
	
}
