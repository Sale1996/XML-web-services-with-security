package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.ExtraField;

public interface ExtraFieldRepository extends JpaRepository<ExtraField, Long> {

	Optional<ExtraField> findByExtraFieldName(String extraFieldName);

}
