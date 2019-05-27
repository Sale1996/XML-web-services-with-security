package com.tim9.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.userservice.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	public Optional<Admin> findById(long id);
	public Admin deleteById(long id);
}
