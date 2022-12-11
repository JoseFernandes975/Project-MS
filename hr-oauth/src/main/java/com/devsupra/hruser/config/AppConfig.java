package com.devsupra.hruser.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
public class AppConfig {
	
	//Coloquei os valores no application.properties pois não estava pegando o valor na hora de buildar imagem docker
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Bean
	public BCryptPasswordEncoder passwordEnconder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAccessTokenConverter tokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtSecret);
		return tokenConverter;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenConverter());
	}
}