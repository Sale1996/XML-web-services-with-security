package com.tim9.pkiapi.certificate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuerDTO {

	private Long id;
	private String CN;
	private String O;
	private String C;
	private String publicKey;
	
}
