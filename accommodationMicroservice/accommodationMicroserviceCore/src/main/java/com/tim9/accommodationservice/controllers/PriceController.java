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

import com.tim9.accommodationservice.services.PriceService;
import com.tim9.accommodationserviceclient.dtos.PriceDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/Price")
@Api(value="Price")
@CrossOrigin(origins = "http://localhost:4200")
public class PriceController {

	@Autowired
	PriceService priceService;
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all prices", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<PriceDTO> > getAllPrices (){
		
		List< PriceDTO > prices = priceService.findAll();
		
		return ( !prices.isEmpty() )? new ResponseEntity< List<PriceDTO> > ( prices, HttpStatus.OK ) : new ResponseEntity< List<PriceDTO> > (prices, HttpStatus.NOT_FOUND );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one Price by id.", notes = "Returns found Price.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< PriceDTO > getOnePriceById (@PathVariable("id") Long id){
		
		PriceDTO priceDTO = priceService.findById(id);
		
		return ( priceDTO.getPriceId()!=null)? new ResponseEntity< PriceDTO > ( priceDTO, HttpStatus.OK ) : new ResponseEntity< PriceDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a Price.", notes = "Returns the Price being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< PriceDTO > addPrice ( @RequestBody PriceDTO dto ){
			
		PriceDTO savedPrice = priceService.save(dto);
		
		return ( savedPrice!=null )? new ResponseEntity< PriceDTO > ( savedPrice, HttpStatus.CREATED ) : new ResponseEntity< PriceDTO > (savedPrice, HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a Price", notes = "Returns the Price being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< PriceDTO > updatePrice (@PathVariable("id") Long id, @RequestBody PriceDTO priceDTO ){
			
		PriceDTO priceToUpdate = priceService.update(id, priceDTO);
		
	    return ( priceToUpdate.getPriceId() != null )? new ResponseEntity< PriceDTO > ( priceToUpdate, HttpStatus.OK ) : new ResponseEntity< PriceDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a Price.", notes = "Returns the Price being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< PriceDTO > deletePrice (@PathVariable("id") Long id){
	
		PriceDTO deletedPriceDTO = priceService.delete(id);
		
		return (deletedPriceDTO.getPriceId() != null ) ? new ResponseEntity< PriceDTO > ( deletedPriceDTO,HttpStatus.OK ) : new ResponseEntity< PriceDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
