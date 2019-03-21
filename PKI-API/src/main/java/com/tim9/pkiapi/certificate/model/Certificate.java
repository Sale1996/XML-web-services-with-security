package com.tim9.pkiapi.certificate.model;

import java.time.LocalDate;
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
	
	@Column(name="cert_path", nullable=false)
	private String path;
	
	@Column(name="serial_number", nullable=false)
	private String serialNumber;
	
	@Column(name="active", nullable=false)
	private boolean active;
	
	@Column(name="valid_from_date", nullable=false)
	private LocalDate validFromDate;
	
	@Column(name="valid_to_date", nullable=false)
	private LocalDate validToDate;
	
	@Column(name= "type", nullable=false)
	private CertificateType type;
	

	
	@JoinColumn(name = "issuer_id", nullable=false)
	@ManyToOne
	private Certificate issuer;
	
	@OneToMany(mappedBy="issuer")
	private Set<Certificate> issued;
	
	public String getTypeString() {
		if (type.equals(CertificateType.FINAL))
			return "Final";
		else if (type.equals(CertificateType.INTERMEDIATE))
			return "Intermediate";
		return "Root";
	}
	
	public void setType(CertificateType certificateType) {
		if (certificateType.equals("Final"))
			this.type = CertificateType.FINAL;
		else if (certificateType.equals("Intermediate"))
			this.type = CertificateType.INTERMEDIATE;
		else
			this.type = CertificateType.ROOT;
	}

}
