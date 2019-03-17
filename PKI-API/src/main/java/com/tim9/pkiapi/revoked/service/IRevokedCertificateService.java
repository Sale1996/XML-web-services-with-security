package com.tim9.pkiapi.revoked.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tim9.pkiapi.revoked.dto.RevokedCertificateDTO;
import com.tim9.pkiapi.revoked.model.RevokedCertificate;

@Service
public interface IRevokedCertificateService {

	public RevokedCertificateDTO findOneById ( Long id );
	
	public RevokedCertificateDTO findOneBySerialNumber ( String serialNumber );
	
	public List<RevokedCertificateDTO> findAll();
	
	public RevokedCertificateDTO save ( RevokedCertificate revokeToSave );
	
	public RevokedCertificateDTO deleteById ( Long id );	
}
