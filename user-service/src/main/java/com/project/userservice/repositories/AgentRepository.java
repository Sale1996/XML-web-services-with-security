package com.project.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.userservice.models.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}
