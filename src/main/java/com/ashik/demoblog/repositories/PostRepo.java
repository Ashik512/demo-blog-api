package com.ashik.demoblog.repositories;

import com.ashik.demoblog.entities.Category;
import com.ashik.demoblog.entities.Post;
import com.ashik.demoblog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);

    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

}
