package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.CategoryDTO;
import com.tim9.agentapp.accommodation.dto.TypeDTO;
import com.tim9.agentapp.accommodation.model.CategoryLocal;
import com.tim9.agentapp.accommodation.repository.CategoryRepository;
import com.tim9.agentapp.accommodation.soapclient.CategoryClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOCategoryConverter;
import com.tim9.agentapp.accommodation.wsdl.Category;
import com.tim9.agentapp.accommodation.wsdl.GetCategoriesResponse;
import com.tim9.agentapp.accommodation.wsdl.GetCitiesResponse;
import com.tim9.agentapp.accommodation.wsdl.GetTypesResponse;
import com.tim9.agentapp.accommodation.wsdl.Type;

@Service
public class CategoryService {
	
	@Autowired
	CategoryClient categoryClient;
	
	@Autowired
	DTOCategoryConverter categoryConverter;
	
	

	public List<CategoryDTO> findAll() {
		
		GetCategoriesResponse response =  categoryClient.getCategories();
		List<CategoryDTO> dtoCategories = new ArrayList<CategoryDTO>();
		
		if(!response.getCategories().isEmpty()) {
			
			for ( Category category : response.getCategories() ) {
				dtoCategories.add(categoryConverter.convertToDTOFromClient(category));	
			}
			
			return dtoCategories;
		}
		
		return Collections.emptyList();

		
	}

//	public CategoryDTO findById(Long id) {
//		
//		Optional< CategoryLocal > category = categoryRepository.findById(id);
//		
//		
//		if ( category.isPresent() ) {
//			
//			return categoryConverter.convertToDTO(category.get());
//		
//		}
//		else {
//			
//			return new CategoryDTO();
//			
//		}
//		
//	}
}
