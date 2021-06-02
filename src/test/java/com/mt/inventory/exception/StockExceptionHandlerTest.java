package com.mt.inventory.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mt.inventory.exception.entity.ApiError;

@SpringBootTest
class StockExceptionHandlerTest {
	
	
	
	@InjectMocks
	StockExceptionHandler stockExceptionHandler;
		
	@Test
	void test() {
		StockNotFoundException snf=new StockNotFoundException("Not found");
		assertNotNull(stockExceptionHandler.stockNotFoundException(snf));
	}

	@Test
	void quantiyTest() {
		QuantityException qe=new QuantityException("Quantity is greater than present");

		assertNotNull(stockExceptionHandler.quantityException(qe));
	}	
	
	@Test
	void nullCheckTest() {
		NullCheckException ne=new NullCheckException("Field Can not be empty");
		assertNotNull(stockExceptionHandler.NullCheckExceptionHandler(ne));
	}	
	
	@Test
	void globalTest() {
		Exception ex=new Exception("Exception Occured");
		assertNotNull(stockExceptionHandler.globalException(ex));
	}	
	
}
