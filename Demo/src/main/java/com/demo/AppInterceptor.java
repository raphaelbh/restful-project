package com.demo;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.User;
import com.demo.service.UserService;

public class AppInterceptor implements HandlerInterceptor {
	
	private final static Logger LOG = Logger.getLogger(AppInterceptor.class);
	
	private static final List<String> ALLOW_ACTIONS = Arrays.asList(
		"/users", 
		"/users/login"
	);
	
	@Autowired
	private UserService userService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		if (!ALLOW_ACTIONS.contains(request.getPathInfo()) && !loggedUser(request)) {
		
			LOG.error("Not allowed action [" + request.getPathInfo() + "]");
			return false;
			
		}
		
		return true;
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
			throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
			throws Exception {
		
	}
	
	private boolean loggedUser(HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		User authUser = (User) session.getAttribute(AppConstants.SESSION_ATTRIB_LOGGED_USER);
		
		if (authUser == null) {
			
			String email = request.getHeader(AppConstants.HEADER_PARAM_EMAIL);
			String password = request.getHeader(AppConstants.HEADER_PARAM_PASSWORD);
			
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			
			authUser = userService.login(user).getBody();
			return authUser != null;
			
		} 
		
		return true;
		
	}
	
}