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

import com.tim9.agentapp.accommodation.dto.CityDTO;
import com.tim9.agentapp.accommodation.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cities")
@Api(value="city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {

	@Autowired
	CityService cityService;
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all cities", httpMethod = "GET" )
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )	
	public ResponseEntity< List<CityDTO> > getAllCities (){
		
		List< CityDTO > cities = cityService.findAll();
		
		return ( !cities.isEmpty() )? new ResponseEntity< List<CityDTO> > ( cities, HttpStatus.OK ) : new ResponseEntity< List<CityDTO> > (cities, HttpStatus.OK );
		
	}
	
	
//	@GetMapping("/{id}")
//	@ApiOperation( value = "Finds one city by id.", notes = "Returns found city.", httpMethod="GET")
//	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
//							 @ApiResponse( code = 404, message = "Not Found")})
//	public ResponseEntity< CityDTO > getOneCityById (@PathVariable("id") Long id){
//		
//		CityDTO cityDTO = cityService.findById(id);
//		
//		return ( cityDTO.getCityId()!=null)? new ResponseEntity< CityDTO > ( cityDTO, HttpStatus.OK ) : new ResponseEntity< CityDTO > ( HttpStatus.NOT_FOUND );
//		
//	}	
}
