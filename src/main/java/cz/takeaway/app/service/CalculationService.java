package cz.takeaway.app.service;

import org.springframework.stereotype.Service;

import cz.takeaway.app.dao.CalculationDao;
import cz.takeaway.app.entity.Calculation;

@Service
public class CalculationService {


	private CalculationDao dao;
	
	public CalculationService(CalculationDao dao) {
		this.dao = dao;
	}
	
	public Calculation getById(Long id) {
		return dao.getById(id);
	}
	
	public Calculation save(Calculation enterprise) {
		return dao.save(enterprise);
	}
	
	public Calculation update(Calculation enterprise) {
		return dao.update(enterprise);
	}
	
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
