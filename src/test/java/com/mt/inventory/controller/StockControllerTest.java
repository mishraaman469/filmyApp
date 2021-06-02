package com.mt.inventory.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Null;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mt.inventory.entity.Stock;
import com.mt.inventory.entity.User;
import com.mt.inventory.exception.NullCheckException;
import com.mt.inventory.service.StockService;
import com.mt.inventory.service.UserService;

@SpringBootTest
class StockControllerTest {

	@Mock
	StockService stockService;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	StockController stockController;
	
	@Test
	void postStocktest() {
		//Stock stock=new Stock(1,"hp",20,3200,"a");
		Stock stock=new Stock();
		stock.setId(1);
		stock.setName("hp");
		stock.setQuantitys(20);
		stock.setProductType("a");
		stock.setPrice(2000);
		doNothing().when(stockService).saveStock(stock);
		ResponseEntity<String> response= new ResponseEntity<String>("Data Saved Successfully "+stock.getName(),HttpStatus.CREATED);
		assertEquals(response,stockController.saveStock(stock));
	}
	
	void postStockExceptiontest() {
		//Stock stock=new Stock(1,"hp",20,3200,"a");
		Stock stock=new Stock();
		stock.setId(1);
		stock.setName("");
		stock.setQuantitys(20);
		stock.setProductType("a");
		stock.setPrice(2000);
		assertThrows(NullCheckException.class, ()->stockController.saveStock(stock));
	}
	
	@Test
	void getStockTest() {
		List<Stock> stock=new ArrayList<>();
		stock.add(new Stock(1,"hp",20,3200,"a"));
		stock.add(new Stock(2,"dell",20,3200,"a"));
		ResponseEntity<List<Stock>> response=new ResponseEntity<List<Stock>>(stock,HttpStatus.OK);
		when(stockService.getStock()).thenReturn(stock);
		assertEquals(response, stockController.getStock());
	}
	
	
	@Test
	void getStockExceptionTest() {
		List<Stock> stock=new ArrayList<>();
		assertThrows(NullCheckException.class, ()->stockController.getStock());
	}
	
	@Test
	void getByStockName() {
		Stock stock=new Stock(1,"hp",20,3200,"a");
		ResponseEntity<Stock> response=new ResponseEntity<Stock>(stock,HttpStatus.OK);
		String name="hp";
		when(stockService.getStockByName(name)).thenReturn(stock);
		assertEquals(response, stockController.getStockByName(name));
	}
	
	
	@Test
	void getByStockNameException() {
		
		String name="";
		assertThrows(NullCheckException.class, ()->stockController.getStockByName(name));
	}


	@Test
	void postUser() {
		//User user=new User(1,"Aman","123",true,"a");
		User user=new User();
		user.setId(1);
		user.setUserName("aman");
		user.setPassword("123");
		user.setActive(true);
		user.setRole("ROLE_ADMIN");
		ResponseEntity<String> response=new ResponseEntity<String>("Data Saved SuccessFully",HttpStatus.CREATED);
		doNothing().when(userService).saveUser(user);
		assertEquals(response, stockController.saveUser(user));
	}
	
	


}
