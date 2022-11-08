package cz.takeaway.app.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.takeaway.app.dto.UserDto;
import cz.takeaway.app.entity.Credentials;
import cz.takeaway.app.entity.User;
import cz.takeaway.app.enumetation.RoleEnum;
import cz.takeaway.app.service.UserService;

@Component
public class UserTransformer {
	
	@Autowired
	private UserService service;

	
	public UserDto transform(User from) {
		if (from == null) {
			return null;
		}
		UserDto to = new UserDto();
		to.setId(from.getId());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setBirthday(from.getBirthday());
		to.setEnterpriseId(from.getEnterpriseId());
		Credentials creds = from.getCredentials();
		if (creds != null) {
			to.setPassword(creds.getPassword());
			to.setEmail(creds.getEmail());
			to.setRole(creds.getRole().getAuthority());
		}
		return to;
	}

	public User transform(UserDto from) {
		if (from == null) {
			return null;
		}
		User user = service.getById(from.getId());
		user.setBirthday(from.getBirthday());
		user.setEnterpriseId(from.getEnterpriseId());
		user.setFirstName(from.getFirstName());
		user.setLastName(from.getLastName());
		Credentials cred = user.getCredentials();
		cred.setEmail(from.getEmail());
		cred.setPassword(from.getPassword());
		cred.setRole(RoleEnum.valueOf(from.getRole()));
		return user;
	}
}
