package cz.takeaway.app.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cz.takeaway.app.entity.Item;

@Repository
public class ItemDao extends BaseDao<Item, Long> {

	@Transactional
	public List<Item> getItemsByMenu(Long menuId) {
		String sql =  "SELECT * FROM item WHERE menu_id = :menuId";
		Query q = entityManager.createNativeQuery(sql, Item.class);
		q.setParameter("menuId", menuId);
		return q.getResultList();
	}
}
