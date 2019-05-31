package com.tim9.agentapp.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.user.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	public Optional<User> findById(long id);
	public User deleteById(long id);
}	
