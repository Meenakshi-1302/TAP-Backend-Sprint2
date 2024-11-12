package com.rts.tap.daoimplementation;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.RecruitingManagerDao;
import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 04-11-2024
**/

@Repository
@Transactional
public class RecruitingManagerDaoImplementation implements RecruitingManagerDao {

	EntityManager entityManager;
	VendorDao vendorDao;

	public RecruitingManagerDaoImplementation(EntityManager entityManager, VendorDao vendorDao) {
		super();
		this.entityManager = entityManager;
		this.vendorDao = vendorDao;
	}

	/**
	 * this method will accept recruiting manager id and perform fetch operation and return Employee as object
	 * @param(recruiting manager's id) - long  
	 * @return Employee - object
	**/
	@Override
	public Employee getRecruitingManagerById(long id) {
		return entityManager.find(Employee.class, id);
	}

	/**
	 * this method will accept recruiting manager id and perform fetch operation and return all Mrf's
	 * assigned for Recruiting manager
	 * @param(recruiting manager's id) - long  
	 * @return list of MrfRecruiting Manager's - object
	**/
	@SuppressWarnings("unchecked")
	@Override
	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id) {
		String hql = "SELECT mrfs FROM MRFRecruitingManager mrfs WHERE recruitingManager.id = :id";
		Query query = entityManager.createQuery(hql).setParameter("id", id);
		List<MRFRecruitingManager> results = query.getResultList(); 		
		return results != null ? results : Collections.emptyList();
	}

	/**
	 * this method will accept Mrf's id and perform fetch operation and return MRF as object
	 * @param(Mrf's id) - long  
	 * @return MRF - object
	**/
	@Override
	public MRF getMrfById(long id) {
		return entityManager.find(MRF.class, id);
	}

	/**
	 * this method will accept MrfVendor DTo and perform add operation and return MRF as object
	 * @param(Mrf's id) - long  
	 * @return MRF - object
	**/
	@Override
	public String assignMrfToVendor(MRFVendorDto mrfVendorDto) {
		String mrfStatus = "";
		try {
			MRF mrf = getMrfById(mrfVendorDto.getMrfId());
			Vendor vendor = vendorDao.findById(mrfVendorDto.getVendorId());			
			Employee rm = getRecruitingManagerById(mrfVendorDto.getRecrutingManagerId());
			MRFVendor mrfVendor = new MRFVendor();
			mrfVendor.setMrf(mrf);
			mrfVendor.setRecruitingManager(rm);
			mrfVendor.setVendor(vendor);
			mrfVendor.setVendorAssignedStatus(MessageConstants.RECRUITING_MANAGER_MRF_STATUS_ASSIGNED);
			entityManager.persist(mrfVendor);
			mrfStatus = MessageConstants.RECRUITING_MANAGER_ASSIGNED_MRF_SUCCESS;
		} catch (Exception e) {
			System.err.println(e);
			mrfStatus = MessageConstants.RECRUITING_MANAGER_ASSIGNED_MRF_FAILED;
		}
		return mrfStatus;
	}

	/**
	 * this method will perform fetch operation of all Mrf assigned to vendors and return MRFVendor as object
	 * @return list of MRFVendor - object
	**/
	@SuppressWarnings("unchecked")
	@Override
	public List<MRFVendor> getAllMrfsVendors() {
		String hql = "SELECT mrfVen FROM MRFVendor mrfVen";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

}
