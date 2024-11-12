package com.rts.tap.daoimplementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.model.LoginCredentials;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class LoginCredentialsDaoImpl implements LoginCredentialsDao {
    
	@Autowired
	EntityManager eManager;
	
	@Autowired
	SessionFactory sessionFactory;
	
    @Override
    public LoginCredentials save(LoginCredentials loginCredentials) {
        if (loginCredentials.getUserId() == null) {
        	eManager.persist(loginCredentials);
            return loginCredentials;
        } else {
            return eManager.merge(loginCredentials);
        }
    }
    
    public LoginCredentials findEmail(String email) {
    	String hql = "FROM LoginCredentials WHERE userEmail = :email";
        TypedQuery<LoginCredentials> query = eManager.createQuery(hql, LoginCredentials.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } 
    }

    public String getRole(Long userId) {
    	  String jpql = "SELECT r.roleName FROM LoginCredentials lc " + "JOIN lc.employee e " + "JOIN e.role r " + "WHERE lc.userId = :userId";
    	  return eManager.createQuery(jpql, String.class).setParameter("userId", userId).getSingleResult();
    }

}
