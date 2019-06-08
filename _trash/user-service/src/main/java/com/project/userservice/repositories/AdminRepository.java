package com.project.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.userservice.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	public Optional<Admin> findById(long id);
	public Admin deleteById(long id);
}
