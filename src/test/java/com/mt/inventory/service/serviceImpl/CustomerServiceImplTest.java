package com.mt.inventory.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.inventory.entity.Customers;
import com.mt.inventory.entity.Stock;
import com.mt.inventory.repository.CustomersRepository;
import com.mt.inventory.repository.OrderRepository;

@SpringBootTest
class CustomerServiceImplTest {

	@Mock
	CustomersRepository customersRepository;
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	
	@Test
	void test() {
		
		//Customers customer=new Customers(1,"Aman",null);
		Customers customer=new Customers();
		customer.setId(1);
		customer.setName("Aman");
		customer.setOrder(null);
		when(customersRepository.save(customer)).thenReturn(null);
		customerServiceImpl.saveCustomer(customer);
		verify(customersRepository).save(customer);
	}

}
