package com.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.AppConstants;
import com.demo.entity.User;
import com.demo.repository.UserRepository;

@RestController
@RequestMapping(value = "users")
public class UserServiceImpl extends BaseService implements UserService {
	
	private static final long serialVersionUID = -1543736142372299380L;
	
	private final static Logger LOG = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private HttpServletRequest request;
	
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
		
		User authUser = userRepository.findByEmailAndPassword(email, password);
		if (authUser != null) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute(AppConstants.SESSION_ATTRIB_LOGGED_USER, authUser);
			
			LOG.info(authUser + " logged");
			
		}
		
		return new ResponseEntity<User>(authUser, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<Void> logout() {
		
		HttpSession session = request.getSession(true);
		
		User authUser = (User) session.getAttribute(AppConstants.SESSION_ATTRIB_LOGGED_USER);
		session.removeAttribute(AppConstants.SESSION_ATTRIB_LOGGED_USER);
		
		LOG.info(authUser + " logout");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}