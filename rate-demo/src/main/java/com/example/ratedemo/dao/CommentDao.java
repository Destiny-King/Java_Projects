package com.example.ratedemo.dao;

import com.example.ratedemo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {

	List<Comment> findAllByForeignId(Integer foreignId);
}
