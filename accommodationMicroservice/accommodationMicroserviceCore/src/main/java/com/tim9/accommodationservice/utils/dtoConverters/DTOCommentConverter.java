package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.models.Comment;
import com.tim9.accommodationservice.repository.CommentRepository;
import com.tim9.accommodationserviceclient.dtos.CommentDTO;



@Component
public class DTOCommentConverter {

	@Autowired
	public CommentRepository commentRepository;
	
	@Autowired 
	public DTOAccommodationConverter accommodationConverter;
	
	
	
	public CommentDTO convertToDTO (Comment comment) {
		
		CommentDTO dto = new CommentDTO();
		
		dto.setCommentId(comment.getCommentId());
		dto.setAccommodation(accommodationConverter.convertToDTO(comment.getAccommodation()));
		dto.setClient(comment.getClient());
		dto.setCommentBody(comment.getCommentBody());
		dto.setIsApproved(comment.isIsApproved());
		

		return dto;
		
	}
	
	public Comment convertFromDTO( CommentDTO dto ) {
		
		Optional<Comment> comment = commentRepository.findById(dto.getCommentId());
		
		if(comment.isPresent()) {
			
			return comment.get();
			
		}
		
		Comment newCandidate = new Comment();
		
		newCandidate.setCommentId(dto.getCommentId());
		newCandidate.setAccommodation(accommodationConverter.convertFromDTO(dto.getAccommodation()));
		newCandidate.setClient(dto.getClient());
		newCandidate.setCommentBody(dto.getCommentBody());
		newCandidate.setIsApproved(dto.isIsApproved());	
		
		return newCandidate;
		
	}
	
	
}
