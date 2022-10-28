package com.devsupra.hruser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsupra.hruser.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	 
}
