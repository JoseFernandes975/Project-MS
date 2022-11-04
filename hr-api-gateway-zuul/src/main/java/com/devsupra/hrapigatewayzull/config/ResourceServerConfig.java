package com.devsupra.hrapigatewayzull.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.netflix.ribbon.proxy.annotation.Http;

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
	}
}