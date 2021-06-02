package com.mt.inventory.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mt.inventory.entity.MyUserDetails;
import com.mt.inventory.entity.User;
import com.mt.inventory.repository.UserRepository;
import com.mt.inventory.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService,UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findByUserName(username);
		user.orElseThrow(()-> new UsernameNotFoundException("Exception Encountered due to particular name passed "+username));
		return user.map(MyUserDetails::new).get();
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	
		
}
