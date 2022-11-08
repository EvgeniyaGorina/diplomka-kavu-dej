package cz.takeaway.app.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cz.takeaway.app.entity.Item;
import cz.takeaway.app.entity.Menu;

@Repository
public class MenuDao extends BaseDao<Menu, Long> {

	@Transactional
	public List<Menu> getAllMenusWithoutEnterprise() {
		String sql =  "SELECT * FROM menu WHERE enterprise_id IS NULL";
		Query q = entityManager.createNativeQuery(sql, Menu.class);
		return q.getResultList();
	}
}
