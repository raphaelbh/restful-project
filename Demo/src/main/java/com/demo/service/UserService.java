package com.demo.service;

import org.springframework.http.ResponseEntity;

import com.demo.entity.User;

public interface UserService {

	ResponseEntity<User> save(User user);
	ResponseEntity<User> login(User user);

}
