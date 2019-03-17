package com.tim9.pkiapi.certificate.dto;

import java.time.LocalDateTime;

import com.tim9.pkiapi.certificate.model.CertificateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuerDTO {

	private String CN;
	private String O;
	private String C;
}
