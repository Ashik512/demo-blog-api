package com.ashik.demoblog.repositories;

import com.ashik.demoblog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
