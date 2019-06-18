package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.CategoryLocal;


public interface CategoryRepository extends JpaRepository<CategoryLocal,Long> {

	Optional<CategoryLocal> findByCategoryName(String categoryName);

}
