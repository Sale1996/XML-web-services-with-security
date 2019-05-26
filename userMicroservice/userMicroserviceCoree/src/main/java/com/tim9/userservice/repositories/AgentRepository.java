package com.tim9.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.userservice.models.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	
	public Optional<Agent> findById(long id);
	public Agent deleteById(long id);
}
