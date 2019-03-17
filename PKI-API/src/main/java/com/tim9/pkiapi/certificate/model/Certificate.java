package com.tim9.pkiapi.certificate.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="public_key", nullable=false)
	private String publicKey;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="serial_number", nullable=false)
	private Long serialNumber;
	
	@Column(name="active", nullable=false)
	private boolean active;
	
	@Column(name="valid_from_date", nullable=false)
	private LocalDateTime validFromDate;
	
	@Column(name="valid_to_date", nullable=false)
	private LocalDateTime validToDate;
	
	@Column(name= "type", nullable=false)
	private CertificateType type;
	
	@JoinColumn(name = "issuer_id", nullable=false)
	@ManyToOne
	private Certificate issuer;
	
	@OneToMany(mappedBy="issuer")
	private Set<Certificate> issued;

}
