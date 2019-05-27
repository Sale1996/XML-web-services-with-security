package com.tim9.accommodationserviceclient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.accommodationserviceclient.dtos.CommentDTO;


@FeignClient(name="commentClient", url = "https://localhost:8081/comments")
public interface CommentClient {


	@GetMapping("")
	public ResponseEntity< List<CommentDTO> > getAllComments ();
	
	
	@GetMapping("/{id}")
	public ResponseEntity< CommentDTO > getOneCommentById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public ResponseEntity< CommentDTO > addComment ( @RequestBody CommentDTO dto );
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity< CommentDTO > updateComment (@PathVariable("id") Long id, @RequestBody CommentDTO CommentDTO );
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity< CommentDTO > deleteComment (@PathVariable("id") Long id);
	
}
