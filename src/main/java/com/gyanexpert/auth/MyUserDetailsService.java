package com.gyanexpert.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        String[] privilages = {"READ","WRITE"};
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privilages) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        
        CustomUserDetails userDetails = new CustomUserDetails(user, authorities);
        
        
        return userDetails;//new MyUserPrincipal(user,"READ","WRITE");
	}

}
