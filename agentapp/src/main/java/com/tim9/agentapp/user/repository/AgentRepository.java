package com.tim9.agentapp.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.agentapp.user.model.AgentLocal;

public interface AgentRepository extends JpaRepository<AgentLocal, Long> {
	
	@Query(value="SELECT * FROM agent a WHERE a.id = ?1", nativeQuery = true)
	public Optional<AgentLocal> findById(long id);
	public Optional<AgentLocal> findByEmail(String email);
	public Optional<AgentLocal> findByLocalId(long id);
	public void deleteByLocalId(long id);
}
