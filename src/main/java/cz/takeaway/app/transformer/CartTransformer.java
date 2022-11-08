package cz.takeaway.app.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.takeaway.app.dto.CartDto;
import cz.takeaway.app.entity.Cart;
import cz.takeaway.app.enumetation.PaymentMethodEnum;
import cz.takeaway.app.service.UserService;

@Component
public class CartTransformer {
	
	@Autowired
	private UserService userService;
	
	public CartDto transform(Cart from) {
		if (from == null) {
			return null;
		}
		CartDto to = new CartDto();
		to.setPayment(from.getPayment().name());
		if (from.getUser() != null) {
		to.setUser(from.getUser().getId());
		}
		to.setId(from.getId());
		return to;
	}
	
	
	public Cart transform(CartDto from) {
		if (from == null) {
			return null;
		}
		Cart to = new Cart();
		to.setPayment(PaymentMethodEnum.valueOf(from.getPayment()));
		if (from.getUser() != null) {
		to.setUser(userService.getById(from.getUser()));
		}
		to.setId(from.getId());
		return to;
	}

}
