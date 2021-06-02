package com.mt.inventory.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mt.inventory.entity.Customers;
import com.mt.inventory.entity.Orders;
import com.mt.inventory.entity.Product;
import com.mt.inventory.entity.Stock;
import com.mt.inventory.exception.NullCheckException;
import com.mt.inventory.service.OrderService;

@SpringBootTest
class OrderControllerTest {

	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderController orderConroller;
	
	@Test
	void test() {
		List<Orders> orders=new ArrayList<>();
		Orders order1=new Orders(1,null,null,null);
		Orders order2=new Orders(2,null,null,null);
		orders.add(order1);
		orders.add(order2);
		ResponseEntity<List<Orders>> response=new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
		when(orderService.getOrder()).thenReturn(orders);
		assertEquals(response, orderConroller.getOrder());
	}
	
	@Test
	void postOrderTest() {
		List<Stock> stock=new ArrayList<>();
		Stock stock1=new Stock(1,"hp",12,1000,"a");
		Stock stock2=new Stock(2,"dell",12,1000,"a");
		List<Product> product=new ArrayList<>();
		product.add(new Product(1,5,null));
		product.add(new Product(2,5,null));
		stock.add(stock1);
		stock.add(stock2);
		Customers customer=new Customers(1,"Aman",null);
		//Orders order1=new Orders(1,product,customer,stock);
		Orders order1=new Orders();
		order1.setId(1);
		order1.setCustomer(customer);
		order1.setProduct(product);
		order1.setStock(stock);
		doNothing().when(orderService).saveOrder(order1);
		ResponseEntity<String> orders=new ResponseEntity<String>("Data Saved Successfully for order "+order1.getStock().get(0).getName(),HttpStatus.CREATED);
		assertEquals(orders, orderConroller.saveOrder(order1));
	}
	
	@Test
	void postOrderExceptionTest() {
		Orders order=new Orders(1,null,null,null);
		assertThrows(NullCheckException.class, ()->orderConroller.saveOrder(order));
	}
	
	@Test
	void getExceptionTest() {
		List<Orders> orders=new ArrayList<>();
		assertThrows(NullCheckException.class, ()->orderConroller.getOrder());
	}

}
