package com.tim9.pkiapi.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.request.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
