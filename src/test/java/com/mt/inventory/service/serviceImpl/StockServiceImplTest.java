package com.mt.inventory.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.inventory.entity.Stock;
import com.mt.inventory.exception.StockNotFoundException;
import com.mt.inventory.repository.StockRepository;

@SpringBootTest
class StockServiceImplTest {
	
	@Mock
	StockRepository stockRepository;
	
	@InjectMocks
	StockServiceImpl stockServiceImpl;
		
	@Test
	void test() {
		//Stock stock=new Stock(1,"hp",20,3200,"a");
		Stock stock=new Stock();
		stock.setId(1);
		stock.setName("hp");
		stock.setQuantitys(20);
		stock.setProductType("a");
		stock.setPrice(2000);
		when(stockRepository.save(stock)).thenReturn(null);
		stockServiceImpl.saveStock(stock);
		verify(stockRepository,times(1)).save(stock);
	}
	
	@Test
	void getTest() {
		List<Stock> stock=new ArrayList<>();
		Stock stock1=new Stock(1,"hp",20,3200,"a");
		Stock stock2=new Stock(2,"dell",20,3200,"a");
		stock.add(stock1);
		stock.add(stock2);
		when(stockRepository.findAll()).thenReturn(stock);
		assertEquals(stock, stockServiceImpl.getStock());
	}
	
	@Test
	void getByNameTest() {
		//Stock stock1=new Stock(1,"hp",20,3200,"a");
		Stock stock1=new Stock();
		stock1.setId(1);
		stock1.setName("hp");
		stock1.setQuantitys(20);
		stock1.setProductType("a");
		stock1.setPrice(2000);
		String name="hp";
		when(stockRepository.findByName(name)).thenReturn(stock1);
		assertEquals(stock1, stockServiceImpl.getStockByName(name));
	}
	
	@Test
	void getByNameExceptionTest() {
		String name="hp";
		assertThrows(StockNotFoundException.class , ()->stockServiceImpl.getStockByName(name));
	}

}
