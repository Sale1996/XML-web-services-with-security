package com.project.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.userservice.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
	
	public Optional<Message> findById(long id);
	public Message deleteById(long id);
}
