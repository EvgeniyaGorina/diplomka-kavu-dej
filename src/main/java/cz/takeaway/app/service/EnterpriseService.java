package cz.takeaway.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.takeaway.app.dao.EnterpriseDao;
import cz.takeaway.app.entity.Enterprise;
import cz.takeaway.app.entity.Menu;

@Service
public class EnterpriseService {

	
	private EnterpriseDao dao;
	
	public EnterpriseService(EnterpriseDao dao) {
		this.dao = dao;
	}
	
	public List<Enterprise> getAllWithIds(List<Long> id) {
		return dao.getAllWithIds(id);
	}
	
	public List<Enterprise> getAll() {
		return dao.getAll();
	}
	
	public Enterprise getById(Long id) {
		return dao.getById(id);
	}
	
	public Enterprise save(Enterprise enterprise) {
		return dao.save(enterprise);
	}
	
	public Enterprise update(Enterprise enterprise) {
		return dao.update(enterprise);
	}
	
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
