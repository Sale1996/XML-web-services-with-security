package com.tim9.pkiapi.certificate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.certificate.dto.CertificateDTO;
import com.tim9.pkiapi.certificate.model.Certificate;
import com.tim9.pkiapi.certificate.repository.CertificateRepository;
import com.tim9.pkiapi.request.dto.RequestDTO;
import com.tim9.pkiapi.request.model.Request;

@Component
public class CertificateServiceImpl implements ICertificateService {

	@Autowired
	CertificateRepository certificateRepository;
	
	@Override
	public CertificateDTO findOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateDTO findOneBySerialNumber(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDTO> findAll() {
		List<CertificateDTO> certs = new ArrayList<CertificateDTO>();
		
		for (Certificate c  : certificateRepository.findAll()) {
			CertificateDTO cert = new CertificateDTO(c);
			certs.add(cert);
 		}
		return certs;
	}

	@Override
	public CertificateDTO save(CertificateDTO certificateToSave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateDTO deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateDTO revoke(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDTO> findAllIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
