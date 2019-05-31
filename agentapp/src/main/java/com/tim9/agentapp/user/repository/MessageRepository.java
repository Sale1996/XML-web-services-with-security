package com.tim9.agentapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.agentapp.accommodation.model.Comment;
import com.tim9.agentapp.user.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
	
	public Optional<Message> findByMessageId(long id);
	@Query(value = "SELECT * FROM message ORDER BY message.message_time DESC", nativeQuery = true)
	public List<Message> getAllMessagesOrderedByLatest();
}
