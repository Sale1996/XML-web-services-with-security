package com.tim9.agentapp.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.user.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
	
	public Optional<Agent> findById(long id);
}
