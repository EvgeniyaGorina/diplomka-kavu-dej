package cz.takeaway.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.takeaway.app.dao.MenuDao;
import cz.takeaway.app.entity.Menu;

@Service
public class MenuService {

	
	private MenuDao dao;
	
	public MenuService(MenuDao dao) {
		this.dao = dao;
	}
	
	public List<Menu> getAllWithIds(List<Long> id) {
		return dao.getAllWithIds(id);
	}
	
	public List<Menu> getAllMenusWithoutEnterprise() {
		return dao.getAllMenusWithoutEnterprise();
	}
	
	
	public Menu getById(Long id) {
		return dao.getById(id);
	}
	
	public Menu save(Menu enterprise) {
		return dao.save(enterprise);
	}
	
	public Menu update(Menu enterprise) {
		return dao.update(enterprise);
	}
	
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
