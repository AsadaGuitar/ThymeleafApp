package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) {
		Optional<User> user = userRepository.findById(name);
		System.out.println(user.orElse(null).getEncodedPassword());
		if(user.orElse(null) == null) {
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new LoginUserDetails(user.orElse(null));
	}
}
