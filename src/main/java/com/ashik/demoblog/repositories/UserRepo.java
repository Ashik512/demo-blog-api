package com.ashik.demoblog.repositories;

import com.ashik.demoblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
