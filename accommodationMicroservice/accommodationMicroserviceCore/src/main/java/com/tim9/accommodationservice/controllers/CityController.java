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

import com.tim9.accommodationservice.services.CityService;
import com.tim9.accommodationserviceclient.dtos.CityDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cities")
@Api(value="city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {

	
	CityService cityService;
	
	
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all cities", httpMethod = "GET" )
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )	
	public ResponseEntity< List<CityDTO> > getAllCities (){
		
		List< CityDTO > cities = cityService.findAll();
		
		return ( !cities.isEmpty() )? new ResponseEntity< List<CityDTO> > ( cities, HttpStatus.OK ) : new ResponseEntity< List<CityDTO> > (cities, HttpStatus.OK );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one city by id.", notes = "Returns found city.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< CityDTO > getOneCityById (@PathVariable("id") Long id){
		
		CityDTO cityDTO = cityService.findById(id);
		
		return ( cityDTO.getCityId()!=null)? new ResponseEntity< CityDTO > ( cityDTO, HttpStatus.OK ) : new ResponseEntity< CityDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a city.", notes = "Returns the city being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< CityDTO > addCity ( @RequestBody CityDTO dto ){
			
		CityDTO savedCity = cityService.save(dto);
		
		return ( savedCity!=null )? new ResponseEntity< CityDTO > ( savedCity, HttpStatus.CREATED ) : new ResponseEntity< CityDTO > (savedCity, HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a city", notes = "Returns the city being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< CityDTO > updateCity (@PathVariable("id") Long id, @RequestBody CityDTO CityDTO ){
			
		CityDTO cityToUpdate = cityService.update(id, CityDTO);
		
	    return ( cityToUpdate.getCityId() != null )? new ResponseEntity< CityDTO > ( cityToUpdate, HttpStatus.OK ) : new ResponseEntity< CityDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a city.", notes = "Returns the city being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< CityDTO > deleteCity (@PathVariable("id") Long id){
	
		CityDTO deletedCityDTO = cityService.delete(id);
		
		return (deletedCityDTO.getCityId() != null ) ? new ResponseEntity< CityDTO > ( deletedCityDTO,HttpStatus.OK ) : new ResponseEntity< CityDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
