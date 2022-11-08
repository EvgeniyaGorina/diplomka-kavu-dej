package cz.takeaway.app.controller;

import java.util.List;

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

import cz.takeaway.app.entity.Item;
import cz.takeaway.app.service.ItemService;

@RestController
@RequestMapping(BasicController.ROOT_V1 + "/item")
@Validated
public class ItemController {

	
	@Autowired
	private ItemService service;

	@GetMapping("/{id}")
	public Item getItem(@PathVariable Long id) {
		Item enterprise = service.getById(id);
		return enterprise;
	}
	
	
	@GetMapping("/menu/{menuId}")
	public List<Item> getItems(@PathVariable Long menuId) {
		return service.getItemsByMenu(menuId);
	}
	
	@GetMapping("/all")
	public List<Item> getAllItems() {
		return service.getAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public Item createItem(@RequestBody @Valid Item enterprise) {
		return service.save(enterprise);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public Item updateItem(@RequestBody @Valid Item enterprise) {
		return service.update(enterprise);
	}
	
	
	@DeleteMapping("/{id}")
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public String deleteItem(@PathVariable Long id) {
		service.delete(id);
		return "Item id: " + id + "was deleted";
	}
}
