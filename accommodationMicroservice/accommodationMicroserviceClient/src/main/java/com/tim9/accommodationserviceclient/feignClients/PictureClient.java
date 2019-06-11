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

import com.tim9.accommodationserviceclient.dtos.PictureDTO;


@FeignClient(name="pictureClient", url = "http://localhost:8081/pictures")
public interface PictureClient {


	@GetMapping("")
	public ResponseEntity< List<PictureDTO> > getAllPictures ();	
	
	@GetMapping("/{id}")
	public ResponseEntity< PictureDTO > getOnePictureById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public ResponseEntity< PictureDTO > addPicture  ( @RequestBody PictureDTO dto );
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity< PictureDTO > updatePicture  (@PathVariable("id") Long id, @RequestBody PictureDTO pictureDTO );
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity< PictureDTO > deletePicture  (@PathVariable("id") Long id);
	
}
