package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.Category;


public interface CategoryRepository extends JpaRepository<Category,Long> {

	Optional<Category> findByCategoryName(String categoryName);

}
