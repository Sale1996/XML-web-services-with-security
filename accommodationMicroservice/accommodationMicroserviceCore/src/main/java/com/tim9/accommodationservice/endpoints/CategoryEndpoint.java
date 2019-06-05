package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetCategoriesRequest;
import com.tim9.accommodationservice.models.GetCategoriesResponse;
import com.tim9.accommodationservice.services.CategoryService;

@Endpoint
public class CategoryEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private CategoryService categoryService;
	
	@Autowired
	public CategoryEndpoint(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoriesRequest")
	@ResponsePayload
	public GetCategoriesResponse getCategories(@RequestPayload GetCategoriesRequest request) {
		GetCategoriesResponse response = new GetCategoriesResponse();
		response.setCategories(categoryService.getAllCategoriesSoap());
		return response;
	}
}