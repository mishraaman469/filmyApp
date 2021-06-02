package com.mt.inventory.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.inventory.entity.Customers;
import com.mt.inventory.entity.Orders;
import com.mt.inventory.entity.Product;
import com.mt.inventory.entity.Stock;
import com.mt.inventory.exception.StockNotFoundException;
import com.mt.inventory.repository.OrderRepository;
import com.mt.inventory.repository.StockRepository;
import com.mt.inventory.service.OrderService;

@SpringBootTest
class OrderServiceImplTest {

	@Mock
	OrderRepository orderRepository;
	
	@Mock
	StockRepository stockRepository;
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	
	@Test
	void test() {
		List<Stock> stock=new ArrayList<>();
		Stock stock1=new Stock(1,"hp",12,1000,"a");
		Stock stock2=new Stock(2,"dell",12,1000,"a");
		List<Product> product=new ArrayList<>();
		product.add(new Product(1,5,null));
		product.add(new Product(2,5,null));
		stock.add(stock1);
		stock.add(stock2);
		Customers customer=new Customers(1,"Aman",null);
		List<Orders> orderList=new ArrayList<>();
		orderList.add(new Orders(1,product,customer,stock));
		when(orderRepository.findAll()).thenReturn(orderList);
		assertEquals(orderList, orderServiceImpl.getOrder());
	}
	
	@Test
	void postTest() {
		List<Stock> stock=new ArrayList<>();
		Stock stock1=new Stock(1,"hp",12,1000,"a");
		Stock stock2=new Stock(2,"dell",12,1000,"a");
		List<Product> product=new ArrayList<>();
		product.add(new Product(1,5,null));
	/*	product.add(product1);
		Product product1=new Product();
		product1.setId(1);
		product1.setQuantity(10);
		product1.setStockAvail(null);*/
		product.add(new Product(2,5,null));
		stock.add(stock1);
		stock.add(stock2);
		Customers customer=new Customers(1,"Aman",null);
		Orders order1=new Orders();
		order1.setId(1);
		order1.setCustomer(customer);
		order1.setProduct(product);
		order1.setStock(stock);
		when(stockRepository.findByName(order1.getStock().get(1).getName())).thenReturn(stock2);
		when(stockRepository.findByName(order1.getStock().get(0).getName())).thenReturn(stock1);
		when(orderRepository.save(order1)).thenReturn(null);
		orderServiceImpl.saveOrder(order1);
		verify(orderRepository).save(order1);
	}

	@Test
	void postExceptionTest() {
		String name="hp";
		List<Stock> stock=new ArrayList<>();
		Stock stock1=new Stock(1,"hp",12,1000,"a");
		Stock stock2=new Stock(2,"dell",12,1000,"a");
		List<Product> product=new ArrayList<>();
		product.add(new Product(1,5,null));
	/*	product.add(product1);
		Product product1=new Product();
		product1.setId(1);
		product1.setQuantity(10);
		product1.setStockAvail(null);*/
		product.add(new Product(2,5,null));
		stock.add(stock1);
		stock.add(stock2);
		Customers customer=new Customers(1,"Aman",null);
		Orders order1=new Orders();
		order1.setId(1);
		order1.setCustomer(customer);
		order1.setProduct(product);
		order1.setStock(stock);
		
		assertThrows(StockNotFoundException.class, ()->orderServiceImpl.saveOrder(order1));
	}
	
}
