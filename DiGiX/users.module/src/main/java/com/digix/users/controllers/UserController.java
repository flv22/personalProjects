package com.digix.users.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digix.users.entities.User;
import com.digix.users.factories.UserFactory;
import com.digix.users.services.impl.UserService;

@RestController
public class UserController {
	
	private UserFactory uFactory = new UserFactory();
	private Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userServ;
	
	@CrossOrigin
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestParam(value="email",required=true)String email,
						@RequestParam(value="password",required=true)String password,
						HttpServletRequest request){
		
		log.info("Email: " + email + ", Pass: " + password);
		
		User u = uFactory.getUser();
			u.setEmail(email);
			u.setPassword(password);
			
		userServ.saveUser(u);
		
		HttpHeaders respHeaders = new HttpHeaders();
		
		return new ResponseEntity<String>("Success", respHeaders, HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String getUsers(){
		return "success";
	}
	
	@CrossOrigin
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
	public ResponseEntity<User> login(@RequestParam(value="email", required=true)String email,
										@RequestParam(value="password", required=true)String password,
										HttpServletRequest request){
		
		log.info(email + " - " + password);
		
		User u = uFactory.getUser();
			u.setEmail(email);
			u.setPassword(password);
		User u2 = userServ.login(u);
		
		String message = "Error";
		if(u2 != null)
			message = "Success";
		
		HttpHeaders respHeaders = new HttpHeaders();
		return new ResponseEntity<User>(u2,respHeaders, HttpStatus.ACCEPTED);
	}
}
