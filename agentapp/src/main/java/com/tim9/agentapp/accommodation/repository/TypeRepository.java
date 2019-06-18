package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.TypeLocal;



public interface TypeRepository extends JpaRepository<TypeLocal,Long>{

	Optional<TypeLocal> findByTypeName(String typeName);

}
