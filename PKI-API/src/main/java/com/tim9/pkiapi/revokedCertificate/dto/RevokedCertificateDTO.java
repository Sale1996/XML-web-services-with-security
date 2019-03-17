package com.tim9.pkiapi.revokedCertificate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokedCertificateDTO {
	
	private Long id;
	
	private Long serialNumber;
	
	private String reason;

}
