package com.tim9.pkiapi.revokedCertificate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.pkiapi.revokedCertificate.dto.RevokedCertificateDTO;
import com.tim9.pkiapi.revokedCertificate.service.IRevokedCertificateService;

@RestController
@RequestMapping("/pki/revokedCertificate")
public class RevokedCertificateController {

	@Autowired
	IRevokedCertificateService revokedCertificateService;
	
	
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<RevokedCertificateDTO>> getAllRevokedCertificates(){
		
		List<RevokedCertificateDTO> revokedCertificates = revokedCertificateService.findAll();
		
		return ( !revokedCertificates.isEmpty() )? new ResponseEntity<List<RevokedCertificateDTO>>(revokedCertificates,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RevokedCertificateDTO> getOneRevokedCertificateById (@PathVariable("id") Long id){
		
		RevokedCertificateDTO revokedCertificateDTO = revokedCertificateService.findOneById(id);
		
		return ( revokedCertificateDTO.getId()!=null )? new ResponseEntity<RevokedCertificateDTO>(revokedCertificateDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	@GetMapping("/serialNumber/{serialNumber}")
	public ResponseEntity<RevokedCertificateDTO> getOneRevokedCertificateById (@PathVariable("serialNumber") String serialNumber){
		
		RevokedCertificateDTO revokedCertificateDTO = revokedCertificateService.findOneBySerialNumber(serialNumber);
		
		return ( revokedCertificateDTO.getId()!=null )? new ResponseEntity<RevokedCertificateDTO>(revokedCertificateDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	
	@PostMapping("/")
	public ResponseEntity<RevokedCertificateDTO> saveRevokedCertificate(@RequestBody RevokedCertificateDTO dto){
		
		RevokedCertificateDTO savedRevokedCertificate = revokedCertificateService.save(dto);
		
		return ( savedRevokedCertificate!=null )? new ResponseEntity<RevokedCertificateDTO>(savedRevokedCertificate,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RevokedCertificateDTO> deleteRevokedCertificate(@PathVariable("id") Long id){
		RevokedCertificateDTO deletedRevokedCertificate = revokedCertificateService.deleteById(id);
		
		return (deletedRevokedCertificate.getId() != null ) ? new ResponseEntity<RevokedCertificateDTO>(deletedRevokedCertificate,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
