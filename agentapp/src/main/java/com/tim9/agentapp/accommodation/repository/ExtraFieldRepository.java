package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.ExtraFieldLocal;

public interface ExtraFieldRepository extends JpaRepository<ExtraFieldLocal, Long> {

	Optional<ExtraFieldLocal> findByExtraFieldName(String extraFieldName);

}
