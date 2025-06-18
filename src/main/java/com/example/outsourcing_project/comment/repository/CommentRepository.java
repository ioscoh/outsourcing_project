package com.example.outsourcing_project.comment.repository;

import com.example.outsourcing_project.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
