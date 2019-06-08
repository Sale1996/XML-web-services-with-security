package com.tim9.pkiapi.request.model;

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
@Table(name="requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="common_name", nullable=false)	
	private String commonName;
	
	@Column(name="country", nullable=false, length=2)	
	private String country;
	
	@Column(name="locality", nullable=false)	
	private String locality;
	
	@Column(name="state", nullable=false)	
	private String state;
	
	@Column(name="organisation", nullable=false)	
	private String organisation;
	
	@Column(name="organisation_unit", nullable=false)	
	private String organisationUnit;
	
	@Column(name="public_key", nullable=false)	
	private String publicKey;
	
	@Column(name="email", nullable=true)	
	private String email;
	
	@Column(name="status", nullable=false)
	private RequestStatus status;
	
	
	
}
