package com.devsupra.hrapigatewayzull.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = { "/hr-oauth/oauth/token" };
	
	private static final String[] OPERATOR = { "/hr-worker/**"  };
	
	private static final String[] ADMIN = { "/hr-user/**", "/hr-payroll/**", "/actuator/**", "/hr-worker/actuator/**", "/hr-oauth/actuator/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()  //para a rota PUBLIC todos estão permitidos
		.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATION", "ADMIN") //para a rota OPERATOR com requisição GET, podem acessar qualquer um (Operator ou ADMIN)
		.antMatchers(ADMIN).hasRole("ADMIN") //para a rota Admin é permitido só para ADMIN
		.anyRequest().authenticated(); //Qualquer outros pedidos que tiver tem que estar autenticado
		
		http.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	 CorsConfiguration corsConfig = new CorsConfiguration();
	 corsConfig.setAllowedOrigins(Arrays.asList("*"));
	 corsConfig.setAllowedMethods(Arrays.asList("POST", "DELETE", "PATCH", "PUT", "GET"));
	 corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	 corsConfig.setAllowCredentials(true);
			
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", corsConfig);
	return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}