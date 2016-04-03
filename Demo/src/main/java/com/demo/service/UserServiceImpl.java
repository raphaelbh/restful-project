package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.repository.UserRepository;

@RestController
@RequestMapping(value = "users")
public class UserServiceImpl extends BaseService implements UserService {
	
	private static final long serialVersionUID = -1543736142372299380L;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<User> save(@RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user) {
		
		String email = user.getEmail();
		String password = user.getPassword(); //TODO: encrypt
		
		User loggedUser = userRepository.findByEmailAndPassword(email, password);
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
		
	}
	
}