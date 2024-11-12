package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class RoleDaoImpli implements RoleDao {

	@Autowired
	EntityManager eManager;

	public void save(Role role) {
		eManager.persist(role);
	}
	
	@Override
	public List<Role> getAllRole() {
		String hql = "from Role";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}
	
	@Override
	public void update(Role role) {
		eManager.merge(role);
	}
//
	public Role getRoleById(Long id) {
		return eManager.find(Role.class, id);	
	}
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
	


