package com.tim9.pkiapi.certificate.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.certificate.repository.CertificateRepository;

@Component
public class CertificateDTOConverter {

	@Autowired
	CertificateRepository certificateRepository;
	
	
	
	public CertificateDTO convertToDTO (Certificate bean, IssuerDTO issuer) {
		
		CertificateDTO dto = new CertificateDTO();
		
		dto.setActive(bean.isActive());
		dto.setId(bean.getId());
		//dto.setIssuer(bean.getIssuer());	
		dto.setName(bean.getName());
		dto.setPublicKey(bean.getPublicKey());
		dto.setSerialNumber(bean.getSerialNumber());
		dto.setType(bean.getType());
		dto.setValidFromDate(bean.getValidFromDate());
		dto.setValidToDate(bean.getValidToDate());
		
		return dto;
		
	}
	
	
	public Certificate convertFromDTO (CertificateDTO dto) {
		
		Optional<Certificate> certificate = certificateRepository.findById(dto.getId());
		
		if(certificate.isPresent()) {
			
			return certificate.get();
			
		}
		
		Certificate newCertificate = new Certificate();
		
		
		newCertificate.setActive(dto.isActive());
		newCertificate.setId(dto.getId());
		//dto.setIssuer(bean.getIssuer());	
		newCertificate.setName(dto.getName());
		newCertificate.setPublicKey(dto.getPublicKey());
		newCertificate.setSerialNumber(dto.getSerialNumber());
		newCertificate.setType(dto.getType());
		newCertificate.setValidFromDate(dto.getValidFromDate());
		newCertificate.setValidToDate(dto.getValidToDate());
		
		return newCertificate;
		
		
	}
	
	/*
	public IssuerDTO getIssuer(Long issuerId) {
		
		Optional<Certificate> certificate = certificateRepository.findById(issuerId);
		
		if(certificate.isPresent()) {
			
			IssuerDTO issuer = new IssuerDTO();
			
			issuer.set
			
		}
		
	}
	*/
	
}
