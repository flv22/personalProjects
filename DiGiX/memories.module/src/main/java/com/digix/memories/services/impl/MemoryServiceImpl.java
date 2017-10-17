package com.digix.memories.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.digix.memories.entities.Memory;
import com.digix.memories.services.contracts.MemoryService;

@Service
public class MemoryServiceImpl implements MemoryService {
	
	private Logger log = Logger.getLogger(MemoryServiceImpl.class.getName());
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@SuppressWarnings("unchecked")
	@Override
	public List<Memory> findMemoriesByTag(long id, String tag) {

		Transaction tx = null;
		Session sess = sessionFactory.openSession();
		
		tx = sess.beginTransaction();
		Query<Memory> query = null;
		
		if(tag != null && !tag.equals("")) {
			log.info("Tag received " + tag);
			query = sess.createQuery("from Memory M where M.owner = :uid AND M.taglist LIKE :tagL");
			query.setParameter("uid", id);
			query.setParameter("tagL", "%" + tag + "%");
		}
		else{
			log.info("Tag not received!");
			query = sess.createQuery("from Memory M where M.owner = :uid");
			query.setParameter("uid", id);
		}
		
		List<Memory> result = query.list();
		
		tx.commit();
		
		log.info("Result: ");
		log.info(result);
		
		return result;
	}

	@Override
	public List<Memory> findMemoriesByTagList(long id, List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public boolean saveMemory(Memory mem) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean deleteMemory(Memory mem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Memory updateMemory(Memory mem) {
		// TODO Auto-generated method stub
		return null;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Memory> findMemoriesByTagList(String tags, long id) {
//
//		Transaction tx = null;
//		Session sess = sessionFactory.openSession();
//		
//		tx = sess.beginTransaction();
//		
//		MemoryFactory memFac = new MemoryFactory();
//		Memory m = memFac.getMemory(MemoryType.PHOTO);
//		log.info("Memory date: " + m.getMemDate());
//		
//		Query<Memory> query = null;
//		
//		if(tags != null){
//			log.info("Tag not null!");
//			//query = sess.createQuery("from Memory");
//			query = sess.createQuery("from Memory as m where m.owner = :uid ORDER BY m.memDate"); // AND m.taglist LIKE :tgl
//			query.setParameter("uid", id);
//			//query.setParameter("tagList", tags);
//		}
//		else{
//			query = sess.createQuery("from Memory");
//		}
//		
//		List<Memory> result = query.list();
//		log.info("Result size: " + result.size());
//		tx.commit();
//		
//		return result;
//	}
//
	@Override
	public boolean saveMemory(Memory mem) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		boolean result = false;
		
		try{
			Session sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			sess.save(mem);
			tx.commit();
			result = true;
			log.info("Memory saved");
		}catch(Exception e){
			tx.rollback();
			result = false;
			log.info("Failed to save memory");
			e.printStackTrace();
		}
		
		return result;
	}
	
}