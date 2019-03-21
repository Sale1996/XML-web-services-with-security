package com.tim9.pkiapi.certificate.dto;

import java.time.LocalDateTime;
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

	
	private Long serialNumber;
	
	private boolean active;
	
	private LocalDateTime validFromDate;
	
	private LocalDateTime validToDate;
	
	private CertificateType type;
	
	private IssuerDTO issuer;
	
	
}

