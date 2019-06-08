package com.tim9.pkiapi.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.role.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
