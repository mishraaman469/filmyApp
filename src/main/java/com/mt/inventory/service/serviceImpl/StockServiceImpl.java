package com.mt.inventory.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.mt.inventory.entity.Stock;
import com.mt.inventory.exception.StockNotFoundException;
import com.mt.inventory.repository.StockRepository;
import com.mt.inventory.service.StockService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class StockServiceImpl implements StockService {
	
		@Autowired
		private StockRepository stockRepository;

		@Override
		@Caching(
				put = {@CachePut(cacheNames = "stocks",key = "#stock.id")},
				evict = {@CacheEvict(cacheNames = "AllStocks",allEntries = true)}
				)
		public void saveStock(Stock stock) {
			log.info("Control in service class Stock");
			this.stockRepository.save(stock);
			
		}

		@Override
		@Cacheable(cacheNames = "Allstocks")
		public List<Stock> getStock() {
			log.info("Control in service class Stock");
				return this.stockRepository.findAll();
		}

		@Override
		@Cacheable(cacheNames = "stocks",key = "#name")
		public Stock getStockByName(String name) {
			log.info("Control in service class Stock");
			Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByName(name));
			if(stock.isEmpty()) {
				log.error("Data with that particular name not found"+name);
				throw new StockNotFoundException("Stock with this particular name not found"+name);
			}
			Stock stocks=stock.get();
			return stocks;
		}
}
