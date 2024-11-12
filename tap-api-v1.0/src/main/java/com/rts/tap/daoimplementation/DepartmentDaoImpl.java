package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	EntityManager eManager;

	public void save(Department department) {
		eManager.persist(department);
	}
	
	@Override
	public List<Department> getAllDepartments() {
		String hql = "from Department";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
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

	@Override
	public void update(Department department) {
		eManager.merge(department);
		
	}

	
}
	


