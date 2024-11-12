package com.rts.tap.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.model.Vendor;

import jakarta.mail.MessagingException;

public interface VendorService {
	public Vendor addNewVendor(VendorDto vendor) throws MessagingException;
	
	public String addNewCandidate(CandidateDto candidateDto) throws MessagingException;

	public VendorDto getVendorById(Long id);

	public List<VendorDto> getAllVendors();

	public Vendor updateVendor(Long id, VendorDto vendor);

	public String deleteVendor(Long id);

	public String generateVendorUsername(String name);

	public String generateStrongPassword();
	
	public VendorDto dologin(VendorDto vendorDto);
	
	void saveAllCandidates(List<CandidateDto> candidateDTOs);

	List<String> getCandidateCountBySourceAndYear(Long sourceId, int year);
}
