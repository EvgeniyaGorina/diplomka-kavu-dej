package cz.takeaway.app.service;

import org.springframework.stereotype.Service;
import cz.takeaway.app.dao.OrderDao;
import cz.takeaway.app.entity.Order;

@Service
public class OrderService {


	private OrderDao dao;
	
	public OrderService(OrderDao dao) {
		this.dao = dao;
	}
	
	public Order getOrderByCartId(Long cartId) {
		return dao.getOrderByCartId(cartId);
	}
	
	public Order getById(Long id) {
		return dao.getById(id);
	}
	
	public Order save(Order order) {
		return dao.save(order);
	}
	
	public Order update(Order order) {
		return dao.update(order);
	}
	
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
