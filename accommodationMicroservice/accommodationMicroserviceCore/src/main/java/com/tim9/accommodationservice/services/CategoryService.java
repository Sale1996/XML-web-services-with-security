package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.Category;
import com.tim9.accommodationservice.repository.CategoryRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOCategoryConverter;
import com.tim9.accommodationserviceclient.dtos.CategoryDTO;

@Service
public class CategoryService {
	
	
	CategoryRepository categoryRepository;
	
	DTOCategoryConverter categoryConverter;
	
	
	public CategoryService(CategoryRepository categoryRepository, DTOCategoryConverter categoryConverter) {
		this.categoryRepository = categoryRepository;
		this.categoryConverter = categoryConverter;
	}
	
	

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

	public CategoryDTO save(CategoryDTO dto) {
		
		//checking if there is already Category with the same name
	
		Optional< Category > foundCategory = categoryRepository.findByCategoryName(dto.getCategoryName());
		
		if( foundCategory.isPresent() ) {
			
			return new CategoryDTO();
		
		}
		
		dto.setCategoryId(-1l);
			
		Category category = categoryConverter.convertFromDTO(dto);
		category.setLastUpdated(LocalDateTime.now());
		category = categoryRepository.save(category);
		
		dto.setCategoryId(category.getCategoryId());
		
		return dto;

}

	public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
		
		Optional< Category > categoryForChange = categoryRepository.findById(id);
		
		if( categoryForChange.isPresent() && categoryDTO!=null ) {
			
			//checking if there is already Category with the same name but not same id
			
			Optional<Category> foundSkill = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
			
			if( foundSkill.isPresent() && foundSkill.get().getCategoryId() != categoryForChange.get().getCategoryId() ) {
				
				return new CategoryDTO();
			
			}
										
			categoryForChange.get().setCategoryName(categoryDTO.getCategoryName());
			categoryForChange.get().setLastUpdated(LocalDateTime.now());
	
			categoryRepository.save(categoryForChange.get());
			
			categoryDTO.setCategoryId(categoryForChange.get().getCategoryId());
			
			
			return categoryDTO;
		
		}
		
		return new CategoryDTO();
	}

	public CategoryDTO delete(Long id) {
		
		Optional< Category > categoryToDelete = categoryRepository.findById(id);
		
		if( categoryToDelete.isPresent() ) {
			
			categoryRepository.deleteById(id);
			
			return categoryConverter.convertToDTO(categoryToDelete.get());
		
		}
		
		return new CategoryDTO();
		
	}

	public List<Category> getAllCategoriesSoap() {
		
		Optional< List<Category> > categories = Optional.of( categoryRepository.findAll() );
				
		if ( categories.isPresent() ) {
			
			return categories.get();
			
		}
			
		return Collections.emptyList();

		
	}



}
