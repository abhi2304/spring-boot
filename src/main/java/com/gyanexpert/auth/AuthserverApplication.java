package com.gyanexpert.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.gyanexpert.auth.config.MyUserDetailsService;
import com.gyanexpert.auth.db.User;
import com.gyanexpert.auth.db.UserRepository;

@SpringBootApplication
@RestController
public class AuthserverApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	};
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
	  return authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		/*http//.antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/", "/login**")
        .permitAll().anyRequest().authenticated().and().formLogin().and().httpBasic();*/
		
		http
			.authorizeRequests()
			.anyRequest()
			.authenticated().and().formLogin().and().httpBasic().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	
	@Bean
	public CommandLineRunner demo(UserRepository repo) {
		return (args)->{
			
			repo.save(new User("abhi", passwordEncoder().encode("Abhi"),"Abhishek"));
			repo.save(new User("swati", passwordEncoder().encode("swati"),"Swati"));
			for(User user: repo.findAll())
				System.out.println(user.getUsername()+"Password:"+user.getPassword());
			System.out.println("Password:"+passwordEncoder().encode("acmesecret"));
		};
	}
}
