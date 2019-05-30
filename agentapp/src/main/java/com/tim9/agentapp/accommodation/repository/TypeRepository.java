package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.Type;



public interface TypeRepository extends JpaRepository<Type,Long>{

	Optional<Type> findByTypeName(String typeName);

}
