package com.tim9.pkiapi.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.user.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
