package com.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Serializable> {

	@Query("from User where email = ?1 and password = ?2")
	User findByEmailAndPassword(String email, String password);
	
}