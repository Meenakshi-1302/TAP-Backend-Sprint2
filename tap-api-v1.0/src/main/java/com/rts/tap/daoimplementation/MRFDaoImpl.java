
package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.MRFDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFAgreement;
import com.rts.tap.model.MRFCriteria;
import com.rts.tap.model.MRFStatus;
import com.rts.tap.model.Requirement;
import com.rts.tap.utils.DateUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MRFDaoImpl implements MRFDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public MRF mrfSave(MRF mrf) {

		if (mrf.getRequirement().getRequirementId() == null) {
			throw new IllegalArgumentException("Requirement ID cannot be null.");
		}

		if (mrf.getBusinessUnitHead().getEmployeeId() == null) {
			throw new IllegalArgumentException("BusinessUnitHead Employee ID cannot be null.");
		}

		if (mrf.getClientPartner().getEmployeeId() == null) {
			throw new IllegalArgumentException("ClientPartner Employee ID cannot be null.");
		}

		Requirement requirement = entityManager.find(Requirement.class, mrf.getRequirement().getRequirementId());
		if (requirement == null)
			throw new IllegalArgumentException(
					"Requirement not found with ID: " + mrf.getRequirement().getRequirementId());

		Employee businessUnitHead = entityManager.find(Employee.class, mrf.getBusinessUnitHead().getEmployeeId());
		if (businessUnitHead == null)
			throw new IllegalArgumentException(
					"BusinessUnitHead not found with ID: " + mrf.getBusinessUnitHead().getEmployeeId());

		Employee clientPartner = entityManager.find(Employee.class, mrf.getClientPartner().getEmployeeId());
		if (clientPartner == null)
			throw new IllegalArgumentException(
					"ClientPartner not found with ID: " + mrf.getClientPartner().getEmployeeId());

		mrf.setRequirement(requirement);
		mrf.setBusinessUnitHead(businessUnitHead);
		mrf.setClientPartner(clientPartner);
		mrf.setCreatedAt(DateUtils.getCurrentDate());
		mrf.setUpdatedAt(DateUtils.getCurrentDate());

		entityManager.persist(mrf);
		return mrf;
	}

	@Override
	public MRF mrfUpdate(Long mrfId, MRF mrf) {

		MRF existingMRF = entityManager.find(MRF.class, mrfId);
		if (existingMRF == null) {
			throw new IllegalArgumentException("MRF not found with ID: " + mrfId);
		}

		if (mrf.getRequirement().getRequirementId() == null) {
			throw new IllegalArgumentException("Requirement ID cannot be null.");
		}

		if (mrf.getMrfCriteria().getMrfCriteriaId() == null) {
			throw new IllegalArgumentException("MRFCriteria ID cannot be null.");
		}

		if (mrf.getMrfAgreement().getMrfAgreementId() == null) {
			throw new IllegalArgumentException("MRFAgreement ID cannot be null.");
		}

		if (mrf.getMrfStatus().getMrfStatusId() == null) {
			throw new IllegalArgumentException("MRFStatus ID cannot be null.");
		}

		if (mrf.getBusinessUnitHead().getEmployeeId() == null) {
			throw new IllegalArgumentException("BusinessUnitHead Employee ID cannot be null.");
		}

		if (mrf.getClientPartner().getEmployeeId() == null) {
			throw new IllegalArgumentException("ClientPartner Employee ID cannot be null.");
		}

		Requirement requirement = entityManager.find(Requirement.class, mrf.getRequirement().getRequirementId());
		if (requirement == null)
			throw new IllegalArgumentException(
					"Requirement not found with ID: " + mrf.getRequirement().getRequirementId());

		MRFCriteria mrfCriteria = entityManager.find(MRFCriteria.class, mrf.getMrfCriteria().getMrfCriteriaId());
		if (mrfCriteria == null)
			throw new IllegalArgumentException(
					"MRFCriteria not found with ID: " + mrf.getMrfCriteria().getMrfCriteriaId());

		MRFAgreement mrfAgreement = entityManager.find(MRFAgreement.class, mrf.getMrfAgreement().getMrfAgreementId());
		if (mrfAgreement == null)
			throw new IllegalArgumentException(
					"MRFAgreement not found with ID: " + mrf.getMrfAgreement().getMrfAgreementId());

		MRFStatus mrfStatus = entityManager.find(MRFStatus.class, mrf.getMrfStatus().getMrfStatusId());
		if (mrfStatus == null)
			throw new IllegalArgumentException("MRFStatus not found with ID: " + mrf.getMrfStatus().getMrfStatusId());

		Employee businessUnitHead = entityManager.find(Employee.class, mrf.getBusinessUnitHead().getEmployeeId());
		if (businessUnitHead == null)
			throw new IllegalArgumentException(
					"BusinessUnitHead not found with ID: " + mrf.getBusinessUnitHead().getEmployeeId());

		Employee clientPartner = entityManager.find(Employee.class, mrf.getClientPartner().getEmployeeId());
		if (clientPartner == null)
			throw new IllegalArgumentException(
					"ClientPartner not found with ID: " + mrf.getClientPartner().getEmployeeId());

		existingMRF.setMrfDepartmentName(mrf.getMrfDepartmentName());
		existingMRF.setRequirement(mrf.getRequirement());
		existingMRF.setMrfRequiredTechnology(mrf.getMrfRequiredTechnology());
		existingMRF.setProbableDesignation(mrf.getProbableDesignation());
		existingMRF.setRequiredResourceCount(mrf.getRequiredResourceCount());
		existingMRF.setRequiredSkills(mrf.getRequiredSkills());
		existingMRF.setMrfCriteria(mrfCriteria);
		existingMRF.setMrfAgreement(mrfAgreement);
		existingMRF.setMrfStatus(mrfStatus);

		existingMRF.setBusinessUnitHead(businessUnitHead);
		existingMRF.setClientPartner(clientPartner);
		existingMRF.setUpdatedAt(DateUtils.getCurrentDate());

		entityManager.merge(existingMRF);

		return existingMRF;
	}

	@Override
	public String mrfDelete(Long mrfId) {
		MRF mrf = entityManager.find(MRF.class, mrfId);
		if (mrf != null) {
			entityManager.remove(mrf);
			return "MRF Record Deleted";
		}
		return "Record not Found";

	}

	@Override
	public MRF getMrf(long mrfId) {
		return entityManager.find(MRF.class, mrfId);
	}

	@Override
	public List<MRF> getAllMRF() {
		TypedQuery<MRF> query = entityManager.createQuery("SELECT m FROM MRF m", MRF.class);
		return query.getResultList();
	}

	@Override
	public MRF mrfCriteriaSave(MRFCriteria mrfCriteria) {
		entityManager.persist(mrfCriteria);
		return null;
	}

	@Override
	public MRF mrfAgreementSave(MRFAgreement mrfAgreement) {
		entityManager.persist(mrfAgreement);
		return null;
	}

	@Override
	public MRF mrfStatusSave(MRFStatus mrfStatus) {
		entityManager.persist(mrfStatus);
		return null;
	}

	

	@Override
	public MRF findById(Long mrfId) {
		return entityManager.find(MRF.class, mrfId);
	}
}





