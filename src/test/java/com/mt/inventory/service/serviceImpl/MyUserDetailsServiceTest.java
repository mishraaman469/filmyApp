package com.mt.inventory.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mt.inventory.entity.User;
import com.mt.inventory.repository.StockRepository;
import com.mt.inventory.repository.UserRepository;

@SpringBootTest
class MyUserDetailsServiceTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@InjectMocks
	MyUserDetailsService myUserDetailsService;
	
	@Test
	void test() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		when(userRepository.save(user)).thenReturn(null);
		//assertEquals(user, myUserDetailsService.saveUser(user));
		myUserDetailsService.saveUser(user);
		verify(userRepository,times(1)).save(user);
	}

	@Test
	void ExceptionTest() {
		Optional<User> user=Optional.ofNullable(new User(1,"aman","123",true,"ROLE_ADMIN"));
		String name="aman";
		when(userRepository.findByUserName(name)).thenReturn(user);
		//assertEquals(user, myUserDetailsService.saveUser(user));
		myUserDetailsService.loadUserByUsername(name);
		verify(userRepository,times(1)).findByUserName(name);
	}	
		
}
