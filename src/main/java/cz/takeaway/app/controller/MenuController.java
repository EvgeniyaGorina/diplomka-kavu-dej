package cz.takeaway.app.controller;

import java.util.List;

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

import cz.takeaway.app.dto.MenuDto;
import cz.takeaway.app.entity.Menu;
import cz.takeaway.app.service.MenuService;
import cz.takeaway.app.transformer.MenuTransformer;

@RestController
@RequestMapping(BasicController.ROOT_V1 + "/menu")
@Validated
public class MenuController {

	@Autowired
	private MenuTransformer transformer;
	
	@Autowired
	private MenuService service;

	@GetMapping("/all")
	public List<Menu> getMenu() {
		List<Menu> menu = service.getAllMenusWithoutEnterprise();
		return menu;
	}
	
	@GetMapping("/{id}")
	public Menu getMenu(@PathVariable Long id) {
		Menu menu = service.getById(id);
		return menu;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	public MenuDto createMenu(@RequestBody @Valid MenuDto menu) {
		 Menu entity =  service.save(transformer.transform(menu));
		 return transformer.transform(entity);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	public MenuDto updateMenu(@RequestBody @Valid MenuDto menu) {
		Menu entity = service.update(transformer.transform(menu));
		return transformer.transform(entity);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteMenu(@PathVariable Long id) {
		service.delete(id);
		return "Menu id: " + id + "was deleted";
	}
}
