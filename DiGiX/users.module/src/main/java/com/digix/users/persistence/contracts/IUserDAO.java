package com.digix.users.persistence.contracts;

import com.digix.users.entities.User;

public interface IUserDAO {
	
	public boolean saveUser(User u);
	
	public User login(User u);
}
