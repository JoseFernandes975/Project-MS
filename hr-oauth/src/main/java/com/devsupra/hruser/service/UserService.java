package com.devsupra.hruser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsupra.hruser.entities.User;
import com.devsupra.hruser.feignclients.UserFeignClient;

@Service
public class UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		User user = userFeignClient.findEmail(email).getBody();
		if(user == null) {
			logger.info("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		 }
		 logger.info("Email found: " + email);
	     return user;
			}
}
