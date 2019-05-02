package com.tim9.accommodationservice.controllers;

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

import com.tim9.accommodationservice.dtos.PictureDTO;
import com.tim9.accommodationservice.services.PictureService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/pictures")
@Api(value="pictures")
@CrossOrigin(origins = "http://localhost:4200")
public class PictureController {

	@Autowired
	PictureService pictureService;
	
	
	
	@GetMapping("/")
	@ApiOperation( value = "Returns all pictures", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<PictureDTO> > getAllPictures (){
		
		List< PictureDTO > pictures = pictureService.findAll();
		
		return ( !pictures.isEmpty() )? new ResponseEntity< List<PictureDTO> > ( pictures, HttpStatus.OK ) : new ResponseEntity< List<PictureDTO> > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one Picture by id.", notes = "Returns found Picture.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< PictureDTO > getOnePictureById (@PathVariable("id") Long id){
		
		PictureDTO pictureDTO = pictureService.findById(id);
		
		return ( pictureDTO.getPictureId()!=null)? new ResponseEntity< PictureDTO > ( pictureDTO, HttpStatus.OK ) : new ResponseEntity< PictureDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("/")
	@ApiOperation( value = "Create a Picture.", notes = "Returns the Picture being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< PictureDTO > addPicture  ( @RequestBody PictureDTO dto ){
			
		PictureDTO savedPicture  = pictureService.save(dto);
		
		return ( savedPicture !=null )? new ResponseEntity< PictureDTO > ( savedPicture , HttpStatus.CREATED ) : new ResponseEntity< PictureDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a Picture", notes = "Returns the Picture being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< PictureDTO > updatePicture  (@PathVariable("id") Long id, @RequestBody PictureDTO pictureDTO ){
			
		PictureDTO pictureToUpdate = pictureService.update(id, pictureDTO);
		
	    return ( pictureToUpdate.getPictureId() != null )? new ResponseEntity< PictureDTO > ( pictureToUpdate, HttpStatus.OK ) : new ResponseEntity< PictureDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a Picture.", notes = "Returns the Picture being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< PictureDTO > deletePicture  (@PathVariable("id") Long id){
	
		PictureDTO deletedPictureDTO = pictureService.delete(id);
		
		return (deletedPictureDTO.getPictureId() != null ) ? new ResponseEntity< PictureDTO > ( deletedPictureDTO,HttpStatus.OK ) : new ResponseEntity< PictureDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
