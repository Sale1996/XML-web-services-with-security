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

import com.tim9.accommodationservice.services.CategoryService;
import com.tim9.accommodationserviceclient.dtos.CategoryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/categories")
@Api(value="category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	
	CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	
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
	public ResponseEntity< CategoryDTO > getOneCategoryById (@PathVariable("id") Long id){
		
		CategoryDTO categoryDTO = categoryService.findById(id);
		
		return ( categoryDTO.getCategoryId()!=null)? new ResponseEntity< CategoryDTO > ( categoryDTO, HttpStatus.OK ) : new ResponseEntity< CategoryDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a Category.", notes = "Returns the Category being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< CategoryDTO > addCategory ( @RequestBody CategoryDTO dto ){
			
		CategoryDTO savedCategory = categoryService.save(dto);
		
		return ( savedCategory!=null )? new ResponseEntity< CategoryDTO > ( savedCategory, HttpStatus.CREATED ) : new ResponseEntity< CategoryDTO > (savedCategory, HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a Category", notes = "Returns the Category being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< CategoryDTO > updateCategory (@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO ){
			
		CategoryDTO categoryToUpdate = categoryService.update(id, categoryDTO);
		
	    return ( categoryToUpdate.getCategoryId() != null )? new ResponseEntity< CategoryDTO > ( categoryToUpdate, HttpStatus.OK ) : new ResponseEntity< CategoryDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a Category.", notes = "Returns the Category being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< CategoryDTO > deleteCategory (@PathVariable("id") Long id){
	
		CategoryDTO deletedCategoryDTO = categoryService.delete(id);
		
		return (deletedCategoryDTO.getCategoryId() != null ) ? new ResponseEntity< CategoryDTO > ( deletedCategoryDTO,HttpStatus.OK ) : new ResponseEntity< CategoryDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
