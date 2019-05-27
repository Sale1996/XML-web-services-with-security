package com.tim9.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.userservice.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	public Optional<User> findById(long id);
	public User deleteById(long id);
}	
