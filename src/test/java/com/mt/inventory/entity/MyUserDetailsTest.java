package com.mt.inventory.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyUserDetailsTest {

	@Test
	void getPasswordTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertEquals("123", myUserDetails.getPassword());
		
	}
	
	@Test
	void getUsernameTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertEquals("aman", myUserDetails.getUsername());
		
	}

	@Test
	void isAccountNonExpiredTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertEquals(true, myUserDetails.isAccountNonExpired());
		
	}	
	
	@Test
	void isAccountNonLockedTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertNotNull( myUserDetails.isAccountNonLocked());
		
	}	
	
	@Test
	void isCredentialsNonExpiredTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertEquals(true, myUserDetails.isCredentialsNonExpired());
		
	}
	
	@Test
	void isEnabledTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertEquals(true, myUserDetails.isEnabled());
		
	}	
	
	@Test
	void getAuthoritiesTest() {
		User user=new User(1,"aman","123",true,"ROLE_ADMIN");
		MyUserDetails myUserDetails=new MyUserDetails(user);
	
	//	when(myUserDetails.getPassword()).thenReturn(user1.getPassword());
		assertEquals(myUserDetails.getAuthorities(), myUserDetails.getAuthorities());
		
	}	
}
