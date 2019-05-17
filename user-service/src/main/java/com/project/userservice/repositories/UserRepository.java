package com.project.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.userservice.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	public Optional<User> findById(long id);
	public User deleteById(long id);
}	
