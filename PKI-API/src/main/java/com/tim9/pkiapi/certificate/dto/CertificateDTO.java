package com.tim9.pkiapi.certificate.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.certificate.model.CertificateType;
import com.tim9.pkiapi.user.dto.UserDTO;

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
	
	private CertificateType type;
	
	private IssuerDTO issuer;
	
	
}
