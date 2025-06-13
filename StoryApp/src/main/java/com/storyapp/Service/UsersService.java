package com.storyapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyapp.model.Users;
import com.storyapp.repository.UserRepository;

@Service
public class UsersService {
	@Autowired
	UserRepository userRepository;
	
	public void addUser(Users user) {
		userRepository.save(user);
	}
}
