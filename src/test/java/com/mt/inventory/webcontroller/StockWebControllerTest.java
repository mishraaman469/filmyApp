package com.mt.inventory.webcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockWebControllerTest {
	
	@InjectMocks
	StockWebController stockWebController;
	
	@Test
	void test() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("index", stockWebController.indexPage());
	}
	

	@Test
	void customerTest() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("customer", stockWebController.customerPage());
	}

	@Test
	void orderTest() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("order", stockWebController.orderPage());
	}

	@Test
	void stockTest() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("stock", stockWebController.stockPage());
	}

	@Test
	void getStockTest() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("GetStock", stockWebController.getstockPage());
	}

	@Test
	void getStockByNameTest() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("GetStockName", stockWebController.getstockNamePage());
	}

	@Test
	void userTest() {
		
		//when(stockWebController.indexPage()).thenReturn(str);
		assertEquals("User", stockWebController.saveUser());
	}

	
	

}
