package com.tim9.pkiapi.certificate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.pkiapi.certificate.dto.CertificateDTO;
import com.tim9.pkiapi.certificate.service.ICertificateService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/pki/certificate")
public class CertificateController {

	@Autowired
	ICertificateService certificateService;
	
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@GetMapping("/")
	public ResponseEntity<List<CertificateDTO>> getAllCertificates(){
		
		List<CertificateDTO> certificates = certificateService.findAll();
		
		return ( !certificates.isEmpty() )? new ResponseEntity<List<CertificateDTO>>(certificates,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CertificateDTO> getOneCertificateById (@PathVariable("id") Long id){
		
		CertificateDTO certificateDTO = certificateService.findOneById(id);
		
		return ( certificateDTO.getId()!=null )? new ResponseEntity<CertificateDTO>(certificateDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	@GetMapping("/serialNumber/{serialNumber}")
	public ResponseEntity<CertificateDTO> getOneCertificateById (@PathVariable("serialNumber") String serialNumber){
		
		CertificateDTO certificateDTO = certificateService.findOneBySerialNumber(serialNumber);
		
		return ( certificateDTO.getId()!=null )? new ResponseEntity<CertificateDTO>(certificateDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	
	@PostMapping("/")
	public ResponseEntity<CertificateDTO> saveCertificate(@RequestBody CertificateDTO dto){
		
		CertificateDTO savedCertificate = certificateService.save(dto);
		
		return ( savedCertificate!=null )? new ResponseEntity<CertificateDTO>(savedCertificate,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CertificateDTO> deleteCertificate(@PathVariable("id") Long id){
		
		CertificateDTO deletedCertificate = certificateService.deleteById(id);
		
		return (deletedCertificate.getId() != null ) ? new ResponseEntity<CertificateDTO>(deletedCertificate,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/revoke/{serialNumber}")
	public ResponseEntity<CertificateDTO> revokeCertificate(@PathVariable("serialNumber") String serialNumber, @RequestBody String reason){
		
		CertificateDTO revokedCertificate = certificateService.revoke(serialNumber, reason);
		
		return (revokedCertificate.getId() != null ) ? new ResponseEntity<CertificateDTO>(revokedCertificate,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
	}
	
	@GetMapping("/issuers/")
	public ResponseEntity<List<CertificateDTO>> getAllIssuers(){
		
		List<CertificateDTO> certificates = certificateService.findAllIssuers();
		
		return ( !certificates.isEmpty() )? new ResponseEntity<List<CertificateDTO>>(certificates,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}
