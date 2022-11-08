package cz.takeaway.app.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.takeaway.app.dto.OrderItemDto;
import cz.takeaway.app.entity.OrderItem;
import cz.takeaway.app.enumetation.SizeEnum;
import cz.takeaway.app.service.ItemService;

@Component
public class OrderItemTransformer {
	
	@Autowired
	private ItemService itemService;

	public OrderItemDto transform(OrderItem from) {
		OrderItemDto to = new OrderItemDto();
		to.setId(from.getId());
		to.setItem(from.getItem().getId());
		to.setSize(from.getSize().name());
		to.setPrice(from.getPrice());
		return to;
	}

	public OrderItem transform(OrderItemDto from) {
		OrderItem to = new OrderItem();
		to.setId(from.getId());
		to.setItem(itemService.getById(from.getItem()));
		to.setPrice(from.getPrice());
		to.setSize(SizeEnum.valueOf(from.getSize()));
		return to;
	}
	
	
	public List<OrderItemDto> transformEntity(List<OrderItem> fromItems) {
		List<OrderItemDto> result = new ArrayList<OrderItemDto>();
		fromItems.forEach(item -> 
			result.add(transform(item))
		);
		return result;
	}
	
	public List<OrderItem> transformDomain(List<OrderItemDto> fromItems) {
		List<OrderItem> result = new ArrayList<OrderItem>();
		fromItems.forEach(item -> 
			result.add(transform(item))
		);
		return result;
	}
	
}
