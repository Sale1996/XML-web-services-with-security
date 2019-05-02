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

import com.tim9.accommodationservice.dtos.TypeDTO;
import com.tim9.accommodationservice.services.TypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/types")
@Api(value="types")
@CrossOrigin(origins = "http://localhost:4200")
public class TypeController {

	@Autowired
	TypeService typeService;
	
	
	
	@GetMapping("/")
	@ApiOperation( value = "Returns all types", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<TypeDTO> > getAllTypes (){
		
		List< TypeDTO > types = typeService.findAll();
		
		return ( !types.isEmpty() )? new ResponseEntity< List<TypeDTO> > ( types, HttpStatus.OK ) : new ResponseEntity< List<TypeDTO> > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one type by id.", notes = "Returns found type.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< TypeDTO > getOneTypeById (@PathVariable("id") Long id){
		
		TypeDTO typeDTO = typeService.findById(id);
		
		return ( typeDTO.getTypeId()!=null)? new ResponseEntity< TypeDTO > ( typeDTO, HttpStatus.OK ) : new ResponseEntity< TypeDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("/")
	@ApiOperation( value = "Create a type.", notes = "Returns the type being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< TypeDTO > addType ( @RequestBody TypeDTO dto ){
			
		TypeDTO savedType = typeService.save(dto);
		
		return ( savedType!=null )? new ResponseEntity< TypeDTO > ( savedType, HttpStatus.CREATED ) : new ResponseEntity< TypeDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a type", notes = "Returns the type being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< TypeDTO > updateType (@PathVariable("id") Long id, @RequestBody TypeDTO typeDTO ){
			
		TypeDTO typeToUpdate = typeService.update(id, typeDTO);
		
	    return ( typeToUpdate.getTypeId() != null )? new ResponseEntity< TypeDTO > ( typeToUpdate, HttpStatus.OK ) : new ResponseEntity< TypeDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a type.", notes = "Returns the type being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< TypeDTO > deleteType (@PathVariable("id") Long id){
	
		TypeDTO deletedTypeDTO = typeService.delete(id);
		
		return (deletedTypeDTO.getTypeId() != null ) ? new ResponseEntity< TypeDTO > ( deletedTypeDTO,HttpStatus.OK ) : new ResponseEntity< TypeDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
