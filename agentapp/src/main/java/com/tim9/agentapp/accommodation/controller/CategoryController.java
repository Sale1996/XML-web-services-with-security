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

import com.tim9.agentapp.accommodation.dto.CategoryDTO;
import com.tim9.agentapp.accommodation.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/categories")
@Api(value="category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all categories", httpMethod = "GET" )
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )	
	public ResponseEntity< List<CategoryDTO> > getAllCategories (){
		
		List< CategoryDTO > categories = categoryService.findAll();
		
		return ( !categories.isEmpty() )? new ResponseEntity< List<CategoryDTO> > ( categories, HttpStatus.OK ) : new ResponseEntity< List<CategoryDTO> > (categories, HttpStatus.OK );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one Category by id.", notes = "Returns found Category.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< CategoryDTO > getOneSkillById (@PathVariable("id") Long id){
		
		CategoryDTO categoryDTO = categoryService.findById(id);
		
		return ( categoryDTO.getCategoryId()!=null)? new ResponseEntity< CategoryDTO > ( categoryDTO, HttpStatus.OK ) : new ResponseEntity< CategoryDTO > ( HttpStatus.NOT_FOUND );
		
	}
}
