package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.CategoryDTO;
import com.tim9.agentapp.accommodation.model.Category;
import com.tim9.agentapp.accommodation.repository.CategoryRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOCategoryConverter;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	DTOCategoryConverter categoryConverter;
	
	

	public List<CategoryDTO> findAll() {
		
		Optional< List<Category> > categories = Optional.of( categoryRepository.findAll() );
		
		ArrayList < CategoryDTO > dtoCategories = new ArrayList<CategoryDTO>();
		
		if ( categories.isPresent() ) {
			
			for ( Category category : categories.get() ) {
				
				dtoCategories.add(categoryConverter.convertToDTO(category));
				
			}
			
			return dtoCategories;
			
		}
			
		return Collections.emptyList();

		
	}

	public CategoryDTO findById(Long id) {
		
		Optional< Category > category = categoryRepository.findById(id);
		
		
		if ( category.isPresent() ) {
			
			return categoryConverter.convertToDTO(category.get());
		
		}
		else {
			
			return new CategoryDTO();
			
		}
		
	}
}
