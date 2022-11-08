package cz.takeaway.app.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.takeaway.app.dto.UserDto;
import cz.takeaway.app.entity.Credentials;
import cz.takeaway.app.entity.User;
import cz.takeaway.app.service.UserService;
import cz.takeaway.app.transformer.UserTransformer;

@RestController
@RequestMapping(BasicController.ROOT_V1 + "/user")
@Validated
public class UserController extends BasicController {

	@Autowired
	private UserService service;

	@Autowired
	private UserTransformer transformer;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping
	@RolesAllowed(value = { "ADMIN", "USER", "USER_OWNER" })
	public UserDto authenticated() {
		Credentials cr = (Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User entity = service.findByUsername(cr.getEmail());
		return transformer.transform(entity);
	}

	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable Long id) {
		return transformer.transform(service.getById(id));
	}

	@PostMapping
	public User createUser(@RequestBody @Valid User user) {
		encodePassword(user);
		return service.save(user);
	}

	@PutMapping
	public User updateUser(@RequestBody @Valid UserDto user) {
		User entity = transformer.transform(user);
		if (!user.getPassword().equals(entity.getCredentials().getPassword())) {
			if (!encoder.matches(user.getPassword(), entity.getCredentials().getPassword())) {
				encodePassword(entity);
			}
		}
		return service.update(entity);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		service.delete(id);
		return "User id: " + id + "was deleted";
	}

	private void encodePassword(User user) {
		Credentials credentials = user.getCredentials();
		credentials.setPassword(encoder.encode(credentials.getPassword()));
	}

}
