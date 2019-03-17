package com.tim9.pkiapi.certificate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tim9.pkiapi.certificate.dto.CertificateDTO;

@Service
public interface ICertificateService {

	public CertificateDTO findOneById ( Long id );
	
	public CertificateDTO findOneBySerialNumber ( String serialNumber );
	
	public List<CertificateDTO> findAll();
	
	public CertificateDTO save ( CertificateDTO certificateToSave );
	
	public CertificateDTO deleteById ( Long id );
	
	public CertificateDTO revoke ( String serialNumber );
	
	
	
}
