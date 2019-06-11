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

import com.tim9.accommodationservice.services.ExtraFieldService;
import com.tim9.accommodationserviceclient.dtos.ExtraFieldDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/extraFields")
@Api(value="extraFields")
@CrossOrigin(origins = "http://localhost:4200")
public class ExtraFieldController {

	
	ExtraFieldService extraFieldService;
	
	public ExtraFieldController(ExtraFieldService extraFieldService) {
		this.extraFieldService = extraFieldService;
	}
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all extraFields", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )	
	public ResponseEntity< List<ExtraFieldDTO> > getAllExtraFields (){
		
		List< ExtraFieldDTO > extraFields = extraFieldService.findAll();
		
		return ( !extraFields.isEmpty() )? new ResponseEntity< List<ExtraFieldDTO> > ( extraFields, HttpStatus.OK ) : new ResponseEntity< List<ExtraFieldDTO> > (extraFields, HttpStatus.OK );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one ExtraField by id.", notes = "Returns found ExtraField.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< ExtraFieldDTO > getOneExtraFieldById (@PathVariable("id") Long id){
		
		ExtraFieldDTO extraFieldDTO = extraFieldService.findById(id);
		
		return ( extraFieldDTO.getExtraFieldId()!=null)? new ResponseEntity< ExtraFieldDTO > ( extraFieldDTO, HttpStatus.OK ) : new ResponseEntity< ExtraFieldDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a ExtraField.", notes = "Returns the ExtraField being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< ExtraFieldDTO > addExtraField ( @RequestBody ExtraFieldDTO dto ){
			
		ExtraFieldDTO savedExtraField = extraFieldService.save(dto);
		
		return ( savedExtraField!=null )? new ResponseEntity< ExtraFieldDTO > ( savedExtraField, HttpStatus.CREATED ) : new ResponseEntity< ExtraFieldDTO > (savedExtraField, HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a ExtraField", notes = "Returns the ExtraField being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< ExtraFieldDTO > updateExtraField (@PathVariable("id") Long id, @RequestBody ExtraFieldDTO extraFieldDTO ){
			
		ExtraFieldDTO extraFieldToUpdate = extraFieldService.update(id, extraFieldDTO);
		
	    return ( extraFieldToUpdate.getExtraFieldId() != null )? new ResponseEntity< ExtraFieldDTO > ( extraFieldToUpdate, HttpStatus.OK ) : new ResponseEntity< ExtraFieldDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a ExtraField.", notes = "Returns the ExtraField being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< ExtraFieldDTO > deleteExtraField (@PathVariable("id") Long id){
	
		ExtraFieldDTO deletedExtraFieldDTO = extraFieldService.delete(id);
		
		return (deletedExtraFieldDTO.getExtraFieldId() != null ) ? new ResponseEntity< ExtraFieldDTO > ( deletedExtraFieldDTO,HttpStatus.OK ) : new ResponseEntity< ExtraFieldDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
