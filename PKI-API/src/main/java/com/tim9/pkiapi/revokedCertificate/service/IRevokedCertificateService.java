package com.tim9.pkiapi.revokedCertificate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tim9.pkiapi.revokedCertificate.dto.RevokedCertificateDTO;
import com.tim9.pkiapi.revokedCertificate.model.RevokedCertificate;

@Service
public interface IRevokedCertificateService {

	public RevokedCertificateDTO findOneById ( Long id );
	
	public RevokedCertificateDTO findOneBySerialNumber ( String serialNumber );
	
	public List<RevokedCertificateDTO> findAll();
	
	public RevokedCertificateDTO save ( RevokedCertificateDTO revokeToSave );
	
	public RevokedCertificateDTO deleteById ( Long id );	
}
