package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.VendorOrganizationProfileDAO;
import com.rts.tap.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class VendorOrganizationProfileDAOImpl implements VendorOrganizationProfileDAO {
	
	private EntityManager entityManager;
	
	public VendorOrganizationProfileDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Vendor findVendorOrganizationByVendorId(Long vendorId) {
		String hql = "FROM Vendor v WHERE v.vendorId = :vendorId";
		Query q = entityManager.createQuery(hql, Vendor.class);
		q.setParameter("vendorId", vendorId);
		return (Vendor) q.getSingleResult();
	}

	@Override
	public Vendor updateVendorOrganizationLogo(Vendor vendor) {
		return entityManager.merge(vendor);
	}
	
	

}
