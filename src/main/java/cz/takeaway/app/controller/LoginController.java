package cz.takeaway.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.takeaway.app.dto.LoginDto;
import cz.takeaway.app.service.UserSecurityDetailsService;

@RestController
@RequestMapping(BasicController.ROOT_V1 + "/login")
public class LoginController {
	
	@Autowired
	private UserSecurityDetailsService userService;

	

}
