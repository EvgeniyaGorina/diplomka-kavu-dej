package cz.takeaway.app.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.takeaway.app.dto.CartDto;
import cz.takeaway.app.entity.Cart;
import cz.takeaway.app.service.CartService;
import cz.takeaway.app.transformer.CartTransformer;

@RestController
@RequestMapping(BasicController.ROOT_V1 + "/cart")
@Validated
public class CartController {

	
	@Autowired
	private CartTransformer transformer;
	
	@Autowired
	private CartService service;

	@GetMapping("/{id}")
	public CartDto getCart(@PathVariable Long id) {
		return transformer.transform(service.getById(id));
	}
	
	@GetMapping("/user/{userId}")
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public CartDto getCartByUser(@PathVariable Long userId) {
		return transformer.transform(service.getCartByUserId(userId));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	public CartDto createCart(@RequestBody @Valid CartDto cart) {
		Cart entity = transformer.transform(cart);
		return transformer.transform(service.save(entity));
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	public CartDto updateCart(@RequestBody @Valid CartDto cart) {
		Cart entity = transformer.transform(cart);
		return transformer.transform(service.update(entity));
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteCart(@PathVariable Long id) {
	    service.delete(id);
		return "Cart id: " + id + "was deleted";
	}
	
}
