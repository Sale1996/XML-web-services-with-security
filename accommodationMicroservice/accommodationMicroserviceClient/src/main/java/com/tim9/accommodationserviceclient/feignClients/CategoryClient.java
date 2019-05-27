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

import com.tim9.accommodationserviceclient.dtos.CategoryDTO;


@FeignClient(name="categoryClient", url = "https://localhost:8081/categories")
public interface CategoryClient {
	
	@GetMapping("")
	public ResponseEntity< List<CategoryDTO> > getAllCategories ();
	
	
	@GetMapping("/{id}")
	public ResponseEntity< CategoryDTO > getOneSkillById (@PathVariable("id") Long id);
	
	
	@PostMapping("")
	public ResponseEntity< CategoryDTO > addCategory ( @RequestBody CategoryDTO dto );
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity< CategoryDTO > updateCategory (@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO );
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity< CategoryDTO > deleteCategory (@PathVariable("id") Long id);
	
}
