package com.tim9.pkiapi.request.controller;

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

import com.tim9.pkiapi.request.dto.RequestDTO;
import com.tim9.pkiapi.request.service.IRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/pki/request")
public class RequestController {

	
	@Autowired
	IRequestService requestService;
	
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@GetMapping("/")
	public ResponseEntity<List<RequestDTO>> getAllRequests(){
		
		List<RequestDTO> requests = requestService.findAll();
		
		return ( !requests.isEmpty() )? new ResponseEntity<List<RequestDTO>>(requests,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestDTO> getOneRequestById (@PathVariable("id") Long id){
		
		RequestDTO requestDTO = requestService.findOneById(id);
		
		return ( requestDTO.getId()!=null )? new ResponseEntity<RequestDTO>(requestDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
			
	@PostMapping("/")
	public ResponseEntity<RequestDTO> saveRequest(@RequestBody RequestDTO dto){
		
		RequestDTO savedRequest = requestService.save(dto);
		
		return ( savedRequest!=null )? new ResponseEntity<RequestDTO>(savedRequest,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RequestDTO> deleteRequest(@PathVariable("id") Long id){
		RequestDTO deletedRequest = requestService.deleteById(id);
		
		return (deletedRequest.getId() != null ) ? new ResponseEntity<RequestDTO>(deletedRequest,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/acceptRequest/{id}")
	public ResponseEntity<RequestDTO> acceptRequest(@PathVariable("id") Long id){
		
		RequestDTO acceptedRequest = requestService.acceptRequest(id);
		
		return (acceptedRequest.getId() != null ) ? new ResponseEntity<RequestDTO>(acceptedRequest,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@PutMapping("/declineRequest/{id}")
	public ResponseEntity<RequestDTO> declineRequest(@PathVariable("id") Long id){
		
		RequestDTO declinedRequest = requestService.declineRequest(id);
		
		return (declinedRequest.getId() != null ) ? new ResponseEntity<RequestDTO>(declinedRequest,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
}
