package com.digix.users.persistence.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.digix.users.entities.User;
import com.digix.users.persistence.contracts.IUserDAO;

public class UserDAO implements IUserDAO {
	
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private Logger log = Logger.getLogger(UserDAO.class.getName());
	
	@Override
	public boolean saveUser(User u) {
		// TODO Auto-generated method stub
		Transaction tx = null;		
		boolean result = false;
		
		try{
			Session sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			sess.save(u);
			tx.commit();
			result = true;
			log.info("User saved");
		}catch(Exception e){
			tx.rollback();
			result = false;
			log.info("Failed to save user");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public User login(User u) {
		
		Transaction tx = null;
		boolean result = false;
		Session sess = sessionFactory.openSession();
		
		tx = sess.beginTransaction();
		Query<User> query = null;
		
		query = sess.createQuery("from User U where U.email = :uEmail AND U.password = :uPass");
		query.setParameter("uEmail", u.getEmail());
		query.setParameter("uPass", u.getPassword());
		
		List<User> users = query.getResultList();
		if(users.size() == 0)
			users.add(null);
		
		return users.get(0);
	}

}
