package cz.takeaway.app.dao;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import cz.takeaway.app.entity.Order;

@Repository
public class OrderDao extends BaseDao<Order, Long> {
	
	public Order getOrderByCartId(Long cartId) {
		String sql = "from Order order WHERE order.cart.id = :cartId";
		Query query = entityManager.createQuery(sql);
		query.setParameter("cartId", cartId);
		return (Order) query.getSingleResult();
	}
}
