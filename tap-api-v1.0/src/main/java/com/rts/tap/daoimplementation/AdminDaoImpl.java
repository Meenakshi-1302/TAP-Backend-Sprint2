package com.rts.tap.daoimplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {

	@Autowired
	EntityManager eManager;

	public void save(Admin admin) {
		eManager.persist(admin);
	}

	public Admin findEmail(String email) {
        String hql = "FROM Admin WHERE adminEmail = :email";
        TypedQuery<Admin> query = eManager.createQuery(hql, Admin.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
	}

	 
	
//	
//	@Override
//	public void update(ExporterApplication exp) {
//		eManager.merge(exp);
//	}
//
//	@Override
//	public ExporterApplication getApp(int id) {
//		return eManager.find(ExporterApplication.class, id);	
//	}
//
//	@Override
//	public List<ExporterApplication> findShipStatus(int userId) {
//		String jpql = "SELECT ea FROM ExporterApplication ea WHERE ea.user.userId = :userId";
//
//        TypedQuery<ExporterApplication> query = eManager.createQuery(jpql, ExporterApplication.class);
//        query.setParameter("userId", userId);
//
//        return query.getResultList();
//	}
}
	


