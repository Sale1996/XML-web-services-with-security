package com.tim9.pkiapi.revoked.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokedCertificateDTO {
	
	private long id;
	
	private long serialNumber;
	
	private String reason;

}
