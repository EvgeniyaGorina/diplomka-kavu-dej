package cz.takeaway.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.takeaway.app.dao.UserDao;
import cz.takeaway.app.dto.UserDto;
import cz.takeaway.app.entity.Credentials;
import cz.takeaway.app.entity.Enterprise;
import cz.takeaway.app.entity.User;

@Service
public class UserService {

	private UserDao dao;

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	public List<User> getAllWithIds(List<Long> id) {
		return dao.getAllWithIds(id);
	}

	public User getById(Long id) {
		return dao.getById(id);
	}

	public User save(User user) {
		return dao.save(user);
	}

	public User update(User user) {
		return dao.update(user);
	}

	public boolean delete(Long id) {
		return dao.delete(id);
	}

}
