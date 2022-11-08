package cz.takeaway.app.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.takeaway.app.dto.OrderDto;
import cz.takeaway.app.entity.Order;
import cz.takeaway.app.enumetation.OrderStateEnum;
import cz.takeaway.app.service.CartService;

@Component
public class OrderTransformer {

	@Autowired
	private OrderItemTransformer orderItemTransformer;
	
	@Autowired
	private CartService cartService;
	
	public OrderDto transform(Order from) {
		if (from == null) {
			return null;
		}
		OrderDto to = new OrderDto();
		to.setId(from.getId());
		to.setItems(orderItemTransformer.transformEntity(from.getOrderItems()));
		if (from.getCart() != null) {
			to.setCartId(from.getCart().getId());
		}
		to.setState(from.getState().name());
		to.setCreated(from.getCreated());
		return to;
		}

	public Order transform(OrderDto from) {
		if (from == null) {
			return null;
		}
		Order to = new Order();
		to.setId(from.getId());
		to.setOrderItems(orderItemTransformer.transformDomain(from.getItems()));
		if (from.getCartId() != null) {
			to.setCart(cartService.getById(from.getCartId()));
		}
		if (from.getState() != null) {
			to.setState(OrderStateEnum.valueOf(from.getState()));
		}
		to.setCreated(from.getCreated());
		return to;
		}
	
	
}
