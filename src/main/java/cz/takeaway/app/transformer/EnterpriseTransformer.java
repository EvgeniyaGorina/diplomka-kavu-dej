package cz.takeaway.app.transformer;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.takeaway.app.dto.EnterpriseDto;
import cz.takeaway.app.entity.Enterprise;
import cz.takeaway.app.entity.Menu;
import cz.takeaway.app.entity.User;
import cz.takeaway.app.service.MenuService;
import cz.takeaway.app.service.UserService;

@Component
public class EnterpriseTransformer {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	public EnterpriseDto transform(Enterprise from) {
		if (from == null) {
			return null;
		}
		EnterpriseDto to = new EnterpriseDto();
		to.setAbout(from.getAbout());
		to.setId(from.getId());
		to.setName(from.getName());
		to.setEmployees(
		from.getEmployees().stream().map(User::getId).collect(Collectors.toList()));
		to.setMenus(
				from.getMenus().stream().map(Menu::getId).collect(Collectors.toList()));
		return to;
	}

	public Enterprise transform(EnterpriseDto from) {
		if (from == null) {
			return null;
		}
		Enterprise to = new Enterprise();
		to.setAbout(from.getAbout());
		to.setId(from.getId());
		to.setName(from.getName());
		to.setMenus(menuService.getAllWithIds(from.getMenus()));
		to.setEmployees(userService.getAllWithIds(from.getEmployees()));
		return to;
	}
}
