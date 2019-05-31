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

import com.tim9.agentapp.accommodation.dto.CommentDTO;
import com.tim9.agentapp.accommodation.service.CommentService;

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
}
