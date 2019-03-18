package com.tim9.pkiapi.certificate.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tim9.pkiapi.certificate.dto.CertificateDTO;

@Component
public class CertificateServiceImpl implements ICertificateService {

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
		// TODO Auto-generated method stub
		return null;
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
