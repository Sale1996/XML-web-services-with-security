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
	
	
	
	public CertificateDTO convertToDTO (Certificate bean) {
		
		CertificateDTO dto = new CertificateDTO();
		
		dto.setActive(bean.isActive());
		dto.setId(bean.getId());
		
		
		IssuerDTO issuer = new IssuerDTO();
		issuer.setId(bean.getIssuer().getId());
		issuer.setCN(bean.getIssuer().getCommonName());
		issuer.setC(bean.getIssuer().getCountry());
		issuer.setO(bean.getIssuer().getOrganisation());
		issuer.setPublicKey(bean.getPublicKey());
		dto.setIssuer(issuer);	
		
		
		dto.setCommonName(bean.getCommonName());
		dto.setCountry(bean.getCountry());
		dto.setLocality(bean.getLocality());
		dto.setState(bean.getState());
		dto.setOrganisation(bean.getOrganisation());
		dto.setOrganisationUnit(bean.getOrganisationUnit());
	
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
			
			if(certificate.get().getIssuer().getId() != dto.getIssuer().getId()) {
				
				Optional<Certificate> issuerCertificate = certificateRepository.findById(dto.getIssuer().getId());
				
				if(issuerCertificate.isPresent()) {
					
					certificate.get().setIssuer(issuerCertificate.get());
					
				}
				
			}
			
			return certificate.get();
			
		}
		
		Certificate newCertificate = new Certificate();
		
		if(dto.getIssuer()!=null) {
			
			Optional<Certificate> issuerCertificate = certificateRepository.findById(dto.getIssuer().getId());
			
			if(issuerCertificate.isPresent()) {
				
				newCertificate.setIssuer(issuerCertificate.get());
				
			}
			
		}
		
		newCertificate.setActive(dto.isActive());
//		newCertificate.setId(dto.getId());
		newCertificate.setCommonName(dto.getCommonName());
		newCertificate.setCountry(dto.getCountry());
		newCertificate.setLocality(dto.getLocality());
		newCertificate.setState(dto.getState());
		newCertificate.setOrganisation(dto.getOrganisation());
		newCertificate.setOrganisationUnit(dto.getOrganisationUnit());
		newCertificate.setPublicKey(dto.getPublicKey());
		newCertificate.setSerialNumber(dto.getSerialNumber());
		newCertificate.setType(dto.getType());
		newCertificate.setValidFromDate(dto.getValidFromDate());
		newCertificate.setValidToDate(dto.getValidToDate());
		
		return newCertificate;
		
		
	}
	

	
}

