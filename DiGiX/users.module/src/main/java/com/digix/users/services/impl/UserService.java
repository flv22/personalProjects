package com.digix.users.services.impl;

import org.springframework.stereotype.Service;

import com.digix.users.entities.User;
import com.digix.users.persistence.impl.UserDAO;
import com.digix.users.services.contracts.IUserService;


@Service
public class UserService implements IUserService {
	
	private UserDAO userDao = new UserDAO();
	
	public boolean saveUser(User u){
		
		return userDao.saveUser(u);
	}

	@Override
	public User login(User u) {
		
		return userDao.login(u);
	}
}
