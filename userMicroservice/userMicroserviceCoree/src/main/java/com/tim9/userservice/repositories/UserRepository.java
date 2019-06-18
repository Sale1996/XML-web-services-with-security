package com.tim9.userservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.userservice.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	public Optional<User> findById(long id);
	public Optional<User> findByEmail(String email);
	public User deleteById(long id);
	@Query(value = " SELECT * FROM xml.user where id in ?1 ", nativeQuery = true)
	public List<User> usersByIds(List<Long> ids);
}	
