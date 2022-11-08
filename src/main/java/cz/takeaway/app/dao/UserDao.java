package cz.takeaway.app.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import cz.takeaway.app.entity.User;

@Repository
public class UserDao extends BaseDao<User, Long> {
	
    public User findByUsername(String username) {
    	String sql = "from User user  where user.credentials.email = :username";
    	Query query = entityManager.createQuery(sql, User.class);
    	query.setParameter("username", username);
    	return (User) query.getSingleResult();
    }
	
	
}
