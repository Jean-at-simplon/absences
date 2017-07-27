package fr.simplon.webSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fr.simplon.webSecurity.configuration.AuthEntryPoint;
import fr.simplon.webSecurity.configuration.AuthFailureHandler;
import fr.simplon.webSecurity.configuration.AuthOutSuccessHandler;
import fr.simplon.webSecurity.configuration.AuthSuccessHandler;

/**
 * Securité de l'application
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired	
	AuthEntryPoint authEntryPoint;
	
	@Autowired
	AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	AuthFailureHandler authFailureHandler;
	
	@Autowired	
	AuthOutSuccessHandler authOutSuccessHandler;
	
	//Override la methode configure
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
	  	.and()	
        	.exceptionHandling()
  				.authenticationEntryPoint(authEntryPoint)
  		.and()
  			.formLogin()
  				.usernameParameter("email")
  				.successHandler(authSuccessHandler)
  				.failureHandler(authFailureHandler)
  		.and()
  			.authorizeRequests()
  			.antMatchers("/absence/**").authenticated()
  		.and()
	  		.logout()
	  			.logoutSuccessHandler(authOutSuccessHandler)
  		.and()
  			.csrf()
  			.disable();
	}

}
