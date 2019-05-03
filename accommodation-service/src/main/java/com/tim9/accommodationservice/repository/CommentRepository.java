package com.tim9.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.accommodationservice.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
