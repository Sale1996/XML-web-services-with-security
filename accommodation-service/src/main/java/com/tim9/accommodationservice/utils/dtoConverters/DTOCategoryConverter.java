package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.dtos.CategoryDTO;
import com.tim9.accommodationservice.models.Category;
import com.tim9.accommodationservice.repository.CategoryRepository;


@Component
public class DTOCategoryConverter {

	@Autowired
	public CategoryRepository categoryRepository;
	
	
	
	public CategoryDTO convertToDTO (Category category) {
		
		CategoryDTO dto = new CategoryDTO();
		
		dto.setCategoryId(category.getCategoryId());
		dto.setCategoryName(category.getCategoryName());
		
		return dto;
		
	}
	
	public Category convertFromDTO( CategoryDTO dto ) {
		
		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
		
		if(category.isPresent()) {
			
			return category.get();
			
		}
		
		Category newCandidate = new Category();
		
		newCandidate.setCategoryId(dto.getCategoryId());
		newCandidate.setCategoryName(dto.getCategoryName());
		
		
		return newCandidate;
		
	}
	
	
}
