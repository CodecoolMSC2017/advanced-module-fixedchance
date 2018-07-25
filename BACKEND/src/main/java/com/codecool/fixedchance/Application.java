package com.codecool.fixedchance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@SpringBootApplication
public class Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {

		SpringApplication.run(Application.class);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/register ", "/auth").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	UserDetailsManager userDetailsManager(
			@Autowired DataSource dataSource,
			@Autowired JdbcTemplate jdbcTemplate,
			@Autowired AuthenticationManager authenticationManager) {
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
		userDetailsManager.setJdbcTemplate(jdbcTemplate);
		userDetailsManager.setDataSource(dataSource);
		// Using AuthenticationManager re-authenticates the session on password change.
		userDetailsManager.setAuthenticationManager(authenticationManager);
		return userDetailsManager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
