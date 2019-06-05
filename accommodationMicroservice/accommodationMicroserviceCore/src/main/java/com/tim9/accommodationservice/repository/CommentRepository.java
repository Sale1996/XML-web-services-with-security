package com.tim9.accommodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.accommodationservice.models.Comment;


public interface CommentRepository extends JpaRepository<Comment,Long> {

	List<Comment> findAllByAccommodationAgentId(long id);

}
