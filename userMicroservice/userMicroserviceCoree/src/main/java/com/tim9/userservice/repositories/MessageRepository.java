package com.tim9.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.userservice.models.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
	
	public Optional<Message> findById(long id);
	public Message deleteById(long id);
}
