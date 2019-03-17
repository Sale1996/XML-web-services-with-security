package com.tim9.pkiapi.revoked.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="revoked_certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokedCertificate {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="serial_number", nullable=false)
	private long serialNumber;
	
	@Column(name="reason", nullable=true)
	private String reason;

}
