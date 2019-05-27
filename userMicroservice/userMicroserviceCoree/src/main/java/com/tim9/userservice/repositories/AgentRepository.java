package com.tim9.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.userservice.models.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
	
	public Optional<Agent> findById(long id);
	public Agent deleteById(long id);
}
