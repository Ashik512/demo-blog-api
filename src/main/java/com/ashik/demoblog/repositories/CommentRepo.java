package com.ashik.demoblog.repositories;

import com.ashik.demoblog.entities.Comment;
import com.ashik.demoblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
}
