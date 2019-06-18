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

import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.service.ExtraFieldService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/extraFields")
@Api(value="extraFields")
@CrossOrigin(origins = "http://localhost:4200")
public class ExtraFieldController {

	@Autowired
	ExtraFieldService extraFieldService;
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all extraFields", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )	
	public ResponseEntity< List<ExtraFieldDTO> > getAllExtraFields (){
		
		List< ExtraFieldDTO > extraFields = extraFieldService.findAll();
		
		return ( !extraFields.isEmpty() )? new ResponseEntity< List<ExtraFieldDTO> > ( extraFields, HttpStatus.OK ) : new ResponseEntity< List<ExtraFieldDTO> > (extraFields, HttpStatus.OK );
		
	}
	
	@GetMapping("/unit/{id}")
	@ApiOperation( value = "Returns all extraFields", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )	
	public ResponseEntity< List<ExtraFieldDTO> > getAllExtraFieldsByUnit (@PathVariable("id") Long id){
		
		List< ExtraFieldDTO > extraFields = extraFieldService.findAllByUnit(id);
		
		return ( !extraFields.isEmpty() )? new ResponseEntity< List<ExtraFieldDTO> > ( extraFields, HttpStatus.OK ) : new ResponseEntity< List<ExtraFieldDTO> > (extraFields, HttpStatus.OK );
		
	}
	
	
	
	
//	@GetMapping("/{id}")
//	@ApiOperation( value = "Finds one ExtraField by id.", notes = "Returns found ExtraField.", httpMethod="GET")
//	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
//							 @ApiResponse( code = 404, message = "Not Found")})
//	public ResponseEntity< ExtraFieldDTO > getOneExtraFieldById (@PathVariable("id") Long id){
//		
//		ExtraFieldDTO extraFieldDTO = extraFieldService.findById(id);
//		
//		return ( extraFieldDTO.getExtraFieldId()!=null)? new ResponseEntity< ExtraFieldDTO > ( extraFieldDTO, HttpStatus.OK ) : new ResponseEntity< ExtraFieldDTO > ( HttpStatus.NOT_FOUND );
//		
//	}
}
