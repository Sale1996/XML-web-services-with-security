package com.tim9.agentapp.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.Comment;


public interface CommentRepository extends JpaRepository<Comment,Long> {

}
