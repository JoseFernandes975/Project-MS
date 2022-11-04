package com.devsupra.hrapigatewayzull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {

@Bean
public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
	accessTokenConverter.setSigningKey("MY-SECRET-KEY");
	return accessTokenConverter;
}
	
@Bean
public JwtTokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
}
	
		
}