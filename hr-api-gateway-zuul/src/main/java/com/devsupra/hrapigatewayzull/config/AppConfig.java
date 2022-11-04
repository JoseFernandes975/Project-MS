package com.devsupra.hrapigatewayzull.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
public class AppConfig {
	
	@Value("${jwt.secret}")
	private String jwtSecret;

@Bean
public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
	accessTokenConverter.setSigningKey(jwtSecret);
	return accessTokenConverter;
}
	
@Bean
public JwtTokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
}	
   }