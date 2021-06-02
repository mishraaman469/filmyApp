package com.mt.inventory.service.serviceImpl;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.mt.inventory.entity.Orders;
import com.mt.inventory.entity.Stock;
import com.mt.inventory.exception.QuantityException;
import com.mt.inventory.exception.StockNotFoundException;
import com.mt.inventory.repository.OrderRepository;
import com.mt.inventory.repository.StockRepository;
import com.mt.inventory.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StockRepository stockRepository;

	@Override
	public void saveOrder(Orders orders) {
		
		for (int i = 0; i < orders.getStock().size(); i++) {
			Optional<Stock> stock = Optional
					.ofNullable(this.stockRepository.findByName(orders.getStock().get(i).getName()));
			if (stock.isPresent()) {
				Stock stocks = stock.get();
				orders.getStock().get(i).setId(stocks.getId());
				orders.getProduct().get(i).setStockAvail(stocks);
			} else {
				log.error("Encountered Exception due to passing the data which is not found"
						+ orders.getStock().get(i).getName());
				throw new StockNotFoundException(
						"Stock with this particular name not found" + orders.getStock().get(i).getName());
			}
		}

		log.info("Reached till here setting the value in order entity successfully");
		for (int i = 0; i < orders.getStock().size(); i++) {
			Optional<Stock> stock = Optional.ofNullable(stockRepository.findByName(orders.getStock().get(i).getName()));
			log.info("Control in service class Order");
			if (stock.isPresent()) {
				Stock stocks = stock.get();
				if (orders.getProduct().get(i).getQuantity() > stocks.getQuantitys()) {
					log.error("You are trying to save the quantity greater than the available"
							+ orders.getProduct().get(i).getQuantity());
					throw new QuantityException("Your quantity is greater" + stocks.getQuantitys());
				} else {
					log.info("Going to save the data");
					int quantities = stocks.getQuantitys() - orders.getProduct().get(i).getQuantity();
					stocks.setQuantitys(quantities);
					stockRepository.save(stocks);
				}
			}
		}

		log.info("Control in service class Order going to save data ");
		this.orderRepository.save(orders);

	}

	@Override
	@Cacheable(cacheNames = "AllOrder")
	public List<Orders> getOrder() {
		log.info("Control in service class Order");
		return this.orderRepository.findAll();
	}

}
