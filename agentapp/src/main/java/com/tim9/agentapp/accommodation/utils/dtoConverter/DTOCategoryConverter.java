package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.CategoryDTO;
import com.tim9.agentapp.accommodation.model.CategoryLocal;
import com.tim9.agentapp.accommodation.repository.CategoryRepository;
import com.tim9.agentapp.accommodation.wsdl.Category;


@Component
public class DTOCategoryConverter {

	@Autowired
	public CategoryRepository categoryRepository;
	
	
	
	public CategoryDTO convertToDTO (CategoryLocal category) {
		
		CategoryDTO dto = new CategoryDTO();
		
		dto.setCategoryId(category.getCategoryId());
		dto.setCategoryName(category.getCategoryName());
		
		return dto;
		
	}
	
	public CategoryDTO convertToDTOFromClient (Category category) {
		
		CategoryDTO dto = new CategoryDTO();
		
		dto.setCategoryId(category.getCategoryId());
		dto.setCategoryName(category.getCategoryName());
		
		return dto;
		
	}
	
	public CategoryLocal convertFromDTO( CategoryDTO dto ) {
		
		Optional<CategoryLocal> category = categoryRepository.findById(dto.getCategoryId());
		
		if(category.isPresent()) {
			
			return category.get();
			
		}
		
		CategoryLocal newCandidate = new CategoryLocal();
		
		newCandidate.setCategoryId(dto.getCategoryId());
		newCandidate.setCategoryName(dto.getCategoryName());
		
		
		return newCandidate;
		
	}
	
	
}
