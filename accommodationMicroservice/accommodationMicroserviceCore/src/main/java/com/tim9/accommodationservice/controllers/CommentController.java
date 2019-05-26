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

import com.tim9.accommodationservice.services.CommentService;
import com.tim9.accommodationserviceclient.dtos.CommentDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/comments")
@Api(value="comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	
	
	@GetMapping("")
	@ApiOperation( value = "Returns all comments", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<CommentDTO> > getAllComments (){
		
		List< CommentDTO > comments = commentService.findAll();
		
		return ( !comments.isEmpty() )? new ResponseEntity< List<CommentDTO> > ( comments, HttpStatus.OK ) : new ResponseEntity< List<CommentDTO> > (comments, HttpStatus.NOT_FOUND );
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one Comment by id.", notes = "Returns found Comment.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity< CommentDTO > getOneCommentById (@PathVariable("id") Long id){
		
		CommentDTO commentDTO = commentService.findById(id);
		
		return ( commentDTO.getCommentId()!=null)? new ResponseEntity< CommentDTO > ( commentDTO, HttpStatus.OK ) : new ResponseEntity< CommentDTO > ( HttpStatus.NOT_FOUND );
		
	}
	
	
	@PostMapping("")
	@ApiOperation( value = "Create a Comment.", notes = "Returns the Comment being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity< CommentDTO > addComment ( @RequestBody CommentDTO dto ){
			
		CommentDTO savedComment = commentService.save(dto);
		
		return ( savedComment!=null )? new ResponseEntity< CommentDTO > ( savedComment, HttpStatus.CREATED ) : new ResponseEntity< CommentDTO > (savedComment, HttpStatus.BAD_REQUEST );

	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a Comment", notes = "Returns the Comment being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity< CommentDTO > updateComment (@PathVariable("id") Long id, @RequestBody CommentDTO CommentDTO ){
			
		CommentDTO commentToUpdate = commentService.update(id, CommentDTO);
		
	    return ( commentToUpdate.getCommentId() != null )? new ResponseEntity< CommentDTO > ( commentToUpdate, HttpStatus.OK ) : new ResponseEntity< CommentDTO > ( HttpStatus.BAD_REQUEST );

	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a Comment.", notes = "Returns the Comment being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< CommentDTO > deleteComment (@PathVariable("id") Long id){
	
		CommentDTO deletedCommentDTO = commentService.delete(id);
		
		return (deletedCommentDTO.getCommentId() != null ) ? new ResponseEntity< CommentDTO > ( deletedCommentDTO,HttpStatus.OK ) : new ResponseEntity< CommentDTO > ( HttpStatus.NOT_FOUND );

	}
	
}
