package cz.takeaway.app.transformer;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.takeaway.app.dto.MenuDto;
import cz.takeaway.app.entity.Item;
import cz.takeaway.app.entity.Menu;
import cz.takeaway.app.service.ItemService;

@Component
public class MenuTransformer {

	@Autowired
	private ItemService itemService;
	
	
	public MenuDto transform(Menu from) {
		if (from == null) {
			return null;
		}
		MenuDto to = new MenuDto();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setItems(
				from.getItems().stream().map(Item::getId).collect(Collectors.toList()));
		return to;
	}

	public Menu transform(MenuDto from) {
		if (from == null) {
			return null;
		}
		Menu to = new Menu();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setItems(itemService.getAllWithIds(from.getItems()));
		return to;
	}
}
