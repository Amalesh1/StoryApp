package com.storyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyapp.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Users findByEmail(String username);

	Users findByUserId(long userId);

}
