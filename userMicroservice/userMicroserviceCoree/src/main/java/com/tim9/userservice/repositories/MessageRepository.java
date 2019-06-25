package com.tim9.userservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.userservice.models.Message;
import com.tim9.userserviceClient.dtos.MessageDTO;

public interface MessageRepository extends JpaRepository<Message,Long> {
	
	public Optional<Message> findByMessageId(long id);
	@Query(value = "SELECT * from message", nativeQuery = true)
	public Optional<List<Message>> findMessagesByReservationId(long id);
	public Message deleteById(long id);
	public List<Message> findByUserId(Long id);
	
	
	//
	//
	// TODOOOOOO: Vrati poruke koje su namenjene agentu. Ispravi upit @Nikola
	// 			  Takodje mi vrati poruke sortirane po datumu opadajuce (LIFO)
	//
	//
	//
	
	
	@Query(value = "SELECT * from message where reservation_id in (?1) ", nativeQuery = true)
	public List<Message> getByReservations(List<Long> reservationIds);
}
