package com.mt.inventory.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mt.inventory.entity.Customers;
import com.mt.inventory.exception.NullCheckException;
import com.mt.inventory.service.CustomerService;

@SpringBootTest
class CustomerControllerTest {

	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
		
	@Test
	void test() {
		//Customers customer=new Customers(1,"Aman",null);
		Customers customer=new Customers();
		customer.setId(1);
		customer.setName("Aman");
		customer.setOrder(null);
		ResponseEntity<String> response=new ResponseEntity<String>("Data saved Sucessfully"+customer.getName(),HttpStatus.CREATED);
		doNothing().when(customerService).saveCustomer(customer);
		assertEquals(response, customerController.saveCustomer(customer));
		
		
	}
	@Test
	void postExceptionTest() {
		//Customers customer=new Customers(1,"Aman",null);
		Customers customer=new Customers();
		customer.setId(1);
		customer.setName("");
		customer.setOrder(null);
		assertThrows(NullCheckException.class, () ->customerController.saveCustomer(customer));
		
		
	}


}
