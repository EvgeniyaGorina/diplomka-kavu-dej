package cz.takeaway.app.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public abstract class BaseDao<T extends Serializable, I> {
	
	private final Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;
	
	
	public BaseDao() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public List<T> getAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}
	
	public List<T> getAllWithIds(List<I> ids) {
		 Query query = entityManager.createQuery("from " + clazz.getName() + " where id in (:ids)");
		 query.setParameter("ids", ids);
		 return	query.getResultList();
	}
	
	public T getById(I id) {
		return entityManager.find(clazz, id);
	}
	
	public T save(T entity) {
		entityManager.persist(entity);		
		return entity;
	}
	
	public T update(T entity) {
		return entityManager.merge(entity);		
	}
	
	public boolean delete(I id) {
	     final T entity = getById(id);
	     entityManager.remove(entity);
	     return getById(id) == null;
	}

}
