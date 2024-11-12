package com.rts.tap.daoimplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class BusinessUnitDaoImpl implements BusinessUnitDao {

	@Autowired
	EntityManager eManager;

	public void save(BusinessUnit businessUnit) {
		eManager.persist(businessUnit);
	}
	
//	@Override
//	public List<Organization> getAllOrganization() {
//		String hql = "from Organization";
//		Query query = eManager.createQuery(hql);
//		return query.getResultList();
//	}
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
	


