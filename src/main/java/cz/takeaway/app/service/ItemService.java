package cz.takeaway.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.takeaway.app.dao.ItemDao;
import cz.takeaway.app.entity.Item;

@Service
public class ItemService {

	
	private ItemDao dao;
	
	public ItemService(ItemDao dao) {
		this.dao = dao;
	}
	
	public List<Item> getAll() {
		return dao.getAll();
	}
	
	public List<Item> getAllWithIds(List<Long> id) {
		return dao.getAllWithIds(id);
	}
	
	public List<Item> getItemsByMenu(Long id) {
		return dao.getItemsByMenu(id);
	}
	
	public Item getById(Long id) {
		return dao.getById(id);
	}
	
	public Item save(Item enterprise) {
		return dao.save(enterprise);
	}
	
	public Item update(Item enterprise) {
		return dao.update(enterprise);
	}
	
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
