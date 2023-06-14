package com.example.RestEmployeeCrud.Security;

import javax.sql.DataSource;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {


    @Bean
    public JdbcUserDetailsManager UserDetailsManager(DataSource datasource) {

        JdbcUserDetailsManager thejdbcudm = new JdbcUserDetailsManager(datasource);
        thejdbcudm.setUsersByUsernameQuery(
        		"select user_id, pw, active from members where user_id=?");
        thejdbcudm.setAuthoritiesByUsernameQuery(
        		"select user_id, role from roles where user_id=?");

        return thejdbcudm;
    }

	
	@Bean
	public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer->
		configurer
			.requestMatchers(HttpMethod.GET,"/api/employee").hasRole("EMPLOYEE")
			.requestMatchers(HttpMethod.GET,"/api/employee/**").hasRole("EMPLOYEE")
			.requestMatchers(HttpMethod.POST,"/api/employee").hasRole("MANAGER")
			.requestMatchers(HttpMethod.PUT,"/api/employee").hasRole("MANAGER")
			.requestMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole("ADMIN")
		);
		
		//Use HTTP basic authentication
		http.httpBasic(Customizer.withDefaults());
		
		//disabling Cross-Site Request Forgery(csrf), a protection mechanism
		//which is needed for web application, here we do not need it as it is just
		//a rest API application
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	/*
	 * The below user details is actually hard coded, but we should be getting 
	 * the users and their roles from a Data base which is also possible....
	 * usually the below method is not used in real-time :( .... 
	 * 
	  @Bean
	public InMemoryUserDetailsManager UserDetailsManager() {
		UserDetails john=User.builder()
				.username("john")
				.password("{noop}123")
				.roles("EMPLOYEE")
				.build();
		UserDetails mary=User.builder()
				.username("mary")
				.password("{noop}123")
				.roles("EMPLOYEE" , "MANAGER")
				.build();
		UserDetails susan=User.builder()
				.username("susan")
				.password("{noop}123")
				.roles("EMPLOYEE" , "MANAGER" , "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john,mary,susan);
	}
	*/
	
	/*The below method is to use the user authentication and authorization
	 * details from the a database....spring security provides a default 
	 * schema with two tables named users and authorities, with columns
	 * if we use this default schema of table and column names in our DB 
	 * then spring security will automatically fetch the details for us 
	 * 

	@Bean
	public JdbcUserDetailsManager UserDetailsManager(DataSource datasource) {
		
		return new JdbcUserDetailsManager(datasource);
	}
	
	*/
	

}
