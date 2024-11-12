package com.rts.tap.daoimplementation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.emailservice.VendorMailService;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.Vendor;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * author: Jeevarajan Rajarajacholan 
 * version: v1.0 
 * updated at: 29-10-2024
 **/

@Repository
@Transactional
public class VendorDaoImplementation implements VendorDao {

	private EntityManager entityManager;
	private VendorMailService mailService;

	public VendorDaoImplementation(EntityManager entityManager, VendorMailService mailService) {
		super();
		this.entityManager = entityManager;
		this.mailService = mailService;
	}

	@Override
	public Vendor save(VendorDto vendor) throws MessagingException {		
		Vendor newVendor = new Vendor();
		newVendor.setOrganizationName(vendor.getOrganizationName());
		newVendor.setVendorUsername(vendor.getVendorUsername());
		newVendor.setEmail(vendor.getEmail());
		newVendor.setPassword(vendor.getPassword());
		entityManager.persist(newVendor);
		mailService.sendVendorCredentials(vendor);
		return newVendor;
	}

	@Override
	public Vendor findById(Long id) {
		return entityManager.find(Vendor.class, id);
	}

	@Override
	public List<Vendor> findAllVendor() {
		return entityManager.createQuery("SELECT v FROM Vendor v", Vendor.class).getResultList();
	}

	@Override
	public String deleteById(Long id) {
		Vendor vendor = findById(id);
		if (vendor != null) {
			entityManager.remove(vendor);
			return MessageConstants.VENDOR_DELETED_SUCCESS;
		} else {
			return MessageConstants.VENDOR_DELETED_FAILED;
		}
	}

	@Override
	public Vendor updateVendor(long id, VendorDto vendor) {
		Vendor existingVendor = findById(id);
		if (existingVendor != null) {
			existingVendor.setAddress(vendor.getAddress());
			existingVendor.setContactName(vendor.getContactName());
			existingVendor.setContactNumber(vendor.getContactNumber());
			existingVendor.setEmail(vendor.getEmail());
			existingVendor.setIsPasswordChanged(vendor.getIsPasswordChanged());
			existingVendor.setOrganizationName(vendor.getOrganizationName());
			existingVendor.setPassword(vendor.getPassword());
			existingVendor.setTaxIdentifyNumber(vendor.getTaxIdentifyNumber());
			existingVendor.setVendorOrganizationLogo(vendor.getVendorOrganizationLogo());
			return entityManager.merge(existingVendor);
		} else {
			return null;
		}
	}
	
	@Override
	public Vendor login(VendorDto vendorDto) {
		String username = vendorDto.getVendorUsername();
		String email = vendorDto.getEmail();
		String password = vendorDto.getPassword();
		if(email != null) {
			String emailLoginQuery= "SELECT v FROM Vendor v WHERE v.thirdPartyCredentitals.email=:email AND v.thirdPartyCredentitals.password = :password";
			Query query = entityManager.createQuery(emailLoginQuery).setParameter("email", email).setParameter("password", password);
			return (Vendor)query.getSingleResult(); 			
		} else {
			String usernameLoginQuery= "SELECT v FROM Vendor v WHERE v.thirdPartyCredentitals.username=:username AND v.thirdPartyCredentitals.password = :password";
			Query query = entityManager.createQuery(usernameLoginQuery).setParameter("username", username).setParameter("password", password);
			return (Vendor)query.getSingleResult(); 						
		}			
	}

	@Override
	public String addCandidate(Candidate candidate, MultipartFile candidateResume) {
		if(candidate != null && candidateResume != null) {
			byte[] resumeData;
			try {
				resumeData = candidateResume.getBytes();
			} catch (IOException e) {
				return MessageConstants.RESUME_UPLOAD_FAILURE;
			}
			
			candidate.setCandidateResume(resumeData);
			entityManager.persist(candidate);
			return MessageConstants.CANDIDATE_ADDED_SUCCESS;
		} else {
			
			
			return MessageConstants.CANDIDATE_ADDED_FAILURE;
		}
	}

	@Override
	public MRFCandidate findMRFCandidateById(long sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getCandidateCountBySourceAndYear(Long sourceId, int year) {
		String sql = "SELECT MONTHNAME(c.assigned_at) AS month, COUNT(c.candidate_id) AS count " +
                "FROM Candidate c " +
                "WHERE c.source_id = :source_id " +
                "AND YEAR(c.assigned_at) = :year " +
                "AND c.source = 'VENDOR' " +
                "GROUP BY MONTH(c.assigned_at) " +
                "ORDER BY MONTH(c.assigned_at)";

   // Execute the query and explicitly cast to List<Object[]>
   return (List<Object[]>) entityManager.createNativeQuery(sql)
                                        .setParameter("source_id", sourceId)
                                        .setParameter("year", year)
                                        .getResultList();
	}
	
	  @Override
	    public void saveAll(List<Candidate> candidates) {
	        for (int i = 0; i < candidates.size(); i++) {
	            entityManager.persist(candidates.get(i)); // Persist each candidate entity
	            if (i % 50 == 0) { // Flush and clear after every 50 entities for performance
	                entityManager.flush();
	                entityManager.clear();
	            }
	        }
	    }


}
