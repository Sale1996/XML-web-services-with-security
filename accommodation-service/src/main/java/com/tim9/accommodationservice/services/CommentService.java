package com.tim9.accommodationservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.dtos.CommentDTO;
import com.tim9.accommodationservice.models.Comment;
import com.tim9.accommodationservice.repository.AccommodationUnitRepository;
import com.tim9.accommodationservice.repository.CommentRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationUnitConverter;
import com.tim9.accommodationservice.utils.dtoConverters.DTOCommentConverter;

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

	public CommentDTO save(CommentDTO dto) {
		
		
		dto.setCommentId(-1l);
			
		Comment Comment = commentRepository.save(commentConverter.convertFromDTO(dto));
		
		dto.setCommentId(Comment.getCommentId());
		
		return dto;

	}

	public CommentDTO update(Long id, CommentDTO commentDTO) {
		
		Optional< Comment > commentForChange = commentRepository.findById(id);
		
		if( commentForChange.isPresent() && commentDTO!=null ) {
			
						
			commentForChange.get().setCommentBody(commentDTO.getCommentBody());
			commentForChange.get().setIsApproved(commentDTO.isIsApproved());
			commentForChange.get().setClient(commentDTO.getClient());
			//commentForChange.get().setAccommodation(nesto); tu ide validacija i pretraga dodatna
	
			commentRepository.save(commentForChange.get());
			
			commentDTO.setCommentId(commentForChange.get().getCommentId());
			
			
			return commentDTO;
		
		}
		
		return new CommentDTO();
	}

	public CommentDTO delete(Long id) {
		
		Optional< Comment > commentToDelete = commentRepository.findById(id);
		
		if( commentToDelete.isPresent() ) {
			
			commentRepository.deleteById(id);
			
			return commentConverter.convertToDTO(commentToDelete.get());
		
		}
		
		return new CommentDTO();
		
	}

}
