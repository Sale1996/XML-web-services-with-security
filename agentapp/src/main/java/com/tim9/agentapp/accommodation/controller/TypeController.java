package com.tim9.agentapp.accommodation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.agentapp.accommodation.dto.TypeDTO;
import com.tim9.agentapp.accommodation.service.TypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/types")
@Api(value="types")
@CrossOrigin("http://localhost:4200")
public class TypeController {

	@Autowired
	TypeService typeService;
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all types", httpMethod = "GET" )
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK" ) } )	
	public ResponseEntity< List<TypeDTO> > getAllTypes (){
		
		List< TypeDTO > types = typeService.findAll();
		
		return ( !types.isEmpty() )? new ResponseEntity< List<TypeDTO> > ( types, HttpStatus.OK ) : new ResponseEntity< List<TypeDTO> > (types, HttpStatus.OK );
		
	}
	
	
//	@GetMapping("/{id}")
//	@ApiOperation( value = "Finds one type by id.", notes = "Returns found type.", httpMethod="GET")
//	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
//							 @ApiResponse( code = 404, message = "Not Found")})
//	public ResponseEntity< TypeDTO > getOneTypeById (@PathVariable("id") Long id){
//		
//		TypeDTO typeDTO = typeService.findById(id);
//		
//		return ( typeDTO.getTypeId()!=null)? new ResponseEntity< TypeDTO > ( typeDTO, HttpStatus.OK ) : new ResponseEntity< TypeDTO > ( HttpStatus.NOT_FOUND );
//		
//	}	
}
