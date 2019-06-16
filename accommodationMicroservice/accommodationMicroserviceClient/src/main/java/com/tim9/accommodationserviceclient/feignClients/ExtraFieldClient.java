package com.tim9.accommodationserviceclient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.accommodationserviceclient.dtos.ExtraFieldDTO;



@FeignClient(name="extraFieldClient", url = "http://localhost:8081/extraFields")
public interface ExtraFieldClient {

	
	@GetMapping("")
	public List<ExtraFieldDTO> getAllExtraFields ();	
	
	@GetMapping("/{id}")
	public ExtraFieldDTO getOneExtraFieldById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public ExtraFieldDTO addExtraField ( @RequestBody ExtraFieldDTO dto );
	
	
	
	@PutMapping("/{id}")
	public ExtraFieldDTO updateExtraField (@PathVariable("id") Long id, @RequestBody ExtraFieldDTO extraFieldDTO );
	
	
	@DeleteMapping("/{id}")
	public ExtraFieldDTO deleteExtraField (@PathVariable("id") Long id);
	
}
