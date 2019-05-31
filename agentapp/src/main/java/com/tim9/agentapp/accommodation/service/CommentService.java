package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.CommentDTO;
import com.tim9.agentapp.accommodation.model.Comment;
import com.tim9.agentapp.accommodation.repository.AccommodationUnitRepository;
import com.tim9.agentapp.accommodation.repository.CommentRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOAccommodationUnitConverter;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOCommentConverter;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	AccommodationUnitRepository accommodationUnitRepository;
	
	
	@Autowired
	DTOCommentConverter commentConverter;
	@Autowired
	DTOAccommodationUnitConverter accommodationConverter;
	
	

	public List<CommentDTO> findAll() {
		
		Optional< List<Comment> > comments = Optional.of( commentRepository.findAll() );
		ArrayList < CommentDTO > dtoComments = new ArrayList<CommentDTO>();
		
		if ( comments.isPresent() ) {
			
			for ( Comment comment : comments.get() ) {
				
				dtoComments.add(commentConverter.convertToDTO(comment));
				
			}
			
			return dtoComments;
			
		}
			
		return Collections.emptyList();

		
	}

	public CommentDTO findById(Long id) {
		
		Optional< Comment > comment = commentRepository.findById(id);
		
		
		if ( comment.isPresent() ) {
			
			return commentConverter.convertToDTO(comment.get());
		
		}
		else {
			
			return new CommentDTO();
			
		}
		
	}
}
