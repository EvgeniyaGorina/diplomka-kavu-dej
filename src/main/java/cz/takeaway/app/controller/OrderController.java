package cz.takeaway.app.controller;

import java.util.Date;

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

import cz.takeaway.app.dto.OrderDto;
import cz.takeaway.app.entity.Order;
import cz.takeaway.app.enumetation.OrderStateEnum;
import cz.takeaway.app.service.OrderService;
import cz.takeaway.app.transformer.OrderTransformer;


@RestController
@RequestMapping(BasicController.ROOT_V1 + "/order")
@Validated
public class OrderController {

	
	@Autowired
	private OrderTransformer transformer;
	
	@Autowired
	private OrderService service;

	@GetMapping("/{id}")
	public OrderDto getOrder(@PathVariable Long id) {
		return transformer.transform(service.getById(id));
	}
	
	@GetMapping("/cart/{id}")
	public OrderDto getOrderByCartId(@PathVariable Long id) {
		return transformer.transform(service.getOrderByCartId(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	public OrderDto createOrder(@RequestBody @Valid OrderDto order) {
		Order entity = transformer.transform(order);
		entity.setState(OrderStateEnum.CREATED);
		entity.setCreated(new Date());
		return transformer.transform(service.save(entity));
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	public OrderDto updateOrder(@RequestBody @Valid OrderDto order) {
		Order entity = transformer.transform(order);
		entity.setState(OrderStateEnum.PENDING);
		return transformer.transform(service.update(entity));
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteOrder(@PathVariable Long id) {
	    service.delete(id);
		return "Order id: " + id + "was deleted";
	}
	
}
