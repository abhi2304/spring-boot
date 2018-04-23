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

@SpringBootApplication
@RestController
public class AuthserverApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
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
		
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		/*auth.inMemoryAuthentication().withUser("user")
        .password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("abhi")
        .password("password").roles("USER");*/
		
		//auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider());
	}
	/*
	@Bean
    public static NoOpPasswordEncoder passwordEncoder() {
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}
	
	/*@Autowired
	private UserDetailsService userDetailsService;*/
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
}
