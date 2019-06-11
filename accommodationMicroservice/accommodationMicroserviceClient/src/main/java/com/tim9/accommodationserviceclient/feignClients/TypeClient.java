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

import com.tim9.accommodationserviceclient.dtos.TypeDTO;


@FeignClient(name="typeClient", url = "http://localhost:8081/types")
public interface TypeClient {

	
	@GetMapping("")	
	public ResponseEntity< List<TypeDTO> > getAllTypes ();
	
	
	@GetMapping("/{id}")
	public ResponseEntity< TypeDTO > getOneTypeById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public ResponseEntity< TypeDTO > addType ( @RequestBody TypeDTO dto );
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity< TypeDTO > updateType (@PathVariable("id") Long id, @RequestBody TypeDTO typeDTO );
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity< TypeDTO > deleteType (@PathVariable("id") Long id);
	
}
