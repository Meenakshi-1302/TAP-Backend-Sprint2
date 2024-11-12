package com.rts.tap.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.Vendor;

import jakarta.mail.MessagingException;


/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 04-11-2024
**/

public interface VendorDao {
	
	Vendor save(VendorDto vendor) throws MessagingException;

	Vendor updateVendor(long id, VendorDto existingVendor);
	
	String addCandidate(Candidate candidate, MultipartFile candidateResume) throws MessagingException;

	String deleteById(Long id);

	Vendor findById(Long id);

	List<Vendor> findAllVendor();
	
	Vendor login(VendorDto vendorDto);
	
	void saveAll(List<Candidate> candidates);
	
	MRFCandidate findMRFCandidateById(long sourceId);
	
	List<Object[]> getCandidateCountBySourceAndYear(Long sourceId, int year);

	
	
	
}
