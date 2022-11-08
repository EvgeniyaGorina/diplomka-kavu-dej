package cz.takeaway.app.service;

import org.springframework.stereotype.Service;

import cz.takeaway.app.dao.CartDao;
import cz.takeaway.app.entity.Cart;

@Service
public class CartService {


	private CartDao dao;
	
	public CartService(CartDao dao) {
		this.dao = dao;
	}
	
	public Cart getCartByUserId(Long userId) {
		return dao.getCartByUserId(userId);
	}
	
	public Cart getById(Long id) {
		return dao.getById(id);
	}
	
	public Cart save(Cart cart) {
		return dao.save(cart);
	}
	
	public Cart update(Cart cart) {
		return dao.update(cart);
	}
	
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
