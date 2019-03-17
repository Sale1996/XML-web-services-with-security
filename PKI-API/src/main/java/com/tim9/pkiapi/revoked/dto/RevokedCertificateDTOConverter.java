package com.tim9.pkiapi.revoked.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.revoked.model.RevokedCertificate;
import com.tim9.pkiapi.revoked.repository.RevokedCertificateRepository;

@Component
public class RevokedCertificateDTOConverter {

	@Autowired
	RevokedCertificateRepository revokedCertificateRepository;
	
	public RevokedCertificateDTO convertToDTO(RevokedCertificate bean) {
		
		RevokedCertificateDTO dto = new RevokedCertificateDTO();
		
		dto.setId(bean.getId());
		dto.setReason(bean.getReason());
		dto.setSerialNumber(bean.getSerialNumber());
		
		return dto;
		
	}
	
	
	public RevokedCertificate convertFromDTO (RevokedCertificateDTO dto) {
		
		Optional<RevokedCertificate> bean = revokedCertificateRepository.findById(dto.getId());
		
		if(bean.isPresent()) {
			
			return bean.get();
		}
		
		RevokedCertificate newBean = new RevokedCertificate();
		
		newBean.setId(dto.getId());
		newBean.setReason(dto.getReason());
		newBean.setSerialNumber(dto.getSerialNumber());
		
		return newBean;
	}
}
