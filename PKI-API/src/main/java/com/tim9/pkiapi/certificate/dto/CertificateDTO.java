package com.tim9.pkiapi.certificate.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.tim9.pkiapi.certificate.model.CertificateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {

	private Long id;
	
	private String publicKey;
	
	private String commonName;
	
	private String country;
	
	private String locality;
	
	private String state;
	
	private String organisation;
	
	private String organisationUnit;

	
	private String serialNumber;
	
	private boolean active;
	
	private LocalDate validFromDate;
	
	private LocalDate validToDate;
	
	private CertificateType type;
	
	private IssuerDTO issuer;
	
	
}

