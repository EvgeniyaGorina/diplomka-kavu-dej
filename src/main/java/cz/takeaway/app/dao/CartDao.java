package cz.takeaway.app.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cz.takeaway.app.entity.Cart;

@Repository
public class CartDao extends BaseDao<Cart, Long> {
	
	public Cart getCartByUserId(Long userId) {
		String sql = "from Cart cart where cart.user.id = :userId";
		Query query = entityManager.createQuery(sql);
		query.setParameter("userId", userId);
		return (Cart) query.getSingleResult();
	}

}
