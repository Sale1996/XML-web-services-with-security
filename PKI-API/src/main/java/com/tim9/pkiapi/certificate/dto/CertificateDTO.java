package com.tim9.pkiapi.certificate.dto;

import java.time.LocalDateTime;

import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.certificate.model.CertificateType;
import com.tim9.pkiapi.request.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {

	private Long id;
	
	private String publicKey;
	
	private String name;
	
	private Long serialNumber;
	
	private boolean active;
	
	private LocalDateTime validFromDate;
	
	private LocalDateTime validToDate;
	
	private String type;
	
	private IssuerDTO issuer;
	
	public CertificateDTO(Certificate c) {
		this.id = c.getId();
		this.name = c.getName();
		this.serialNumber = c.getSerialNumber();
		this.active = c.isActive();
		this.validFromDate = c.getValidFromDate();
		this.validToDate = c.getValidToDate();
		this.type = c.getTypeString();
		//this.issuer = c.getIssuer()c.getId();
	}
	
	
}
