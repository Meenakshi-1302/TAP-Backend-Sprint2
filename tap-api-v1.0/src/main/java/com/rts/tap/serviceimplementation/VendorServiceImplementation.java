package com.rts.tap.serviceimplementation;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.exception.VendorNotFoundException;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.VendorService;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

/** 
 * author: Jeevarajan, Vashanth 
 * version: v1.0
 * updated at: 04-11-2024
**/

@Service
@Transactional
public class VendorServiceImplementation implements VendorService {

	private final VendorDao vendorDao;

	public VendorServiceImplementation(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	@Override
	public Vendor addNewVendor(VendorDto vendor) throws MessagingException {
		vendor.setVendorUsername(generateVendorUsername(vendor.getOrganizationName()));
		vendor.setPassword(generateStrongPassword());
		return vendorDao.save(vendor);
	}

	@Override
	public VendorDto getVendorById(Long id) {
		Vendor vendor = vendorDao.findById(id);
		if (vendor == null) {
			throw new VendorNotFoundException(MessageConstants.VENDOR_NOT_FOUND);
		}
		VendorDto vendorDto = new VendorDto();
		BeanUtils.copyProperties(vendor, vendorDto);
		return vendorDto;
	}

	@Override
	public List<VendorDto> getAllVendors() {
		return vendorDao.findAllVendor().stream().map(vendor -> {
			VendorDto vendorDto = new VendorDto();
			BeanUtils.copyProperties(vendor, vendorDto);
			return vendorDto;
		}).collect(Collectors.toList());
	}

	@Override
	public Vendor updateVendor(Long id, VendorDto vendor) {
		Vendor existingVendor = vendorDao.findById(id);
		if (existingVendor == null) {
			throw new VendorNotFoundException(MessageConstants.VENDOR_NOT_FOUND);
		}
//	        BeanUtils.copyProperties(vendorDto, existingVendor, "vendorid");
		return vendorDao.updateVendor(id, vendor);
	}

	@Override
	public String deleteVendor(Long id) {
		try {
			vendorDao.deleteById(id);
			return MessageConstants.VENDOR_DELETED_SUCCESS;
		} catch (Exception e) {
			return MessageConstants.VENDOR_DELETED_FAILED;
		}
	}

	@Override
	public String generateVendorUsername(String name) {
		String prefix = name.length() >= 3 ? name.substring(0, 3).toLowerCase() : name.toLowerCase();
		Random random = new Random();
		StringBuilder digits = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			digits.append(random.nextInt(10)); // Generates a digit between 0 and 9
		}
		String username = prefix + digits.toString();
		return username;
	}

	@Override
	public String generateStrongPassword() {
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(12);
		for (int i = 0; i < 12; i++) {
			int index = random.nextInt(MessageConstants.CHARACTERS.length());
			password.append(MessageConstants.CHARACTERS.charAt(index));
		}
		return password.toString();
	}
	public VendorDto dologin(VendorDto vendorDto) {
		Vendor vendor = vendorDao.login(vendorDto);		
		VendorDto vendorObject = new VendorDto();
		vendorObject.setVendorId(vendor.getVendorId());
		vendorObject.setOrganizationName(vendor.getOrganizationName());
		vendorObject.setVendorUsername(vendor.getThirdPartyCredentitals().getUsername());
		vendorObject.setContactName(vendor.getContactName());
		vendorObject.setContactNumber(vendor.getContactNumber());
		vendorObject.setAddress(vendor.getAddress());
		vendorObject.setEmail(vendor.getThirdPartyCredentitals().getEmail());
		vendorObject.setPassword(vendor.getThirdPartyCredentitals().getPassword());
		vendorObject.setWebsiteUrl(vendor.getWebsiteUrl());
		vendorObject.setTaxIdentifyNumber(vendor.getTaxIdentifyNumber());
		vendorObject.setIsPasswordChanged(vendor.getIsPasswordChanged());
		vendorObject.setRole(vendor.getThirdPartyCredentitals().getRole().getRole());		
		return vendorObject;
	}

	@Override
	public String addNewCandidate(CandidateDto candidateDto) throws MessagingException {
	    // Validate file type and size
	    MultipartFile candidateResume = candidateDto.getCandidateResume();

	    // Check if the file is a PDF
	    if (candidateResume != null && !candidateResume.getContentType().equals("application/pdf")) {
	        return "Only PDF files are allowed";
	    }

	    // Check if the file size is less than or equal to 2MB (2 * 1024 * 1024 bytes)
	    long maxSizeInBytes = 2 * 1024 * 1024;  // 2MB
	    if (candidateResume != null && candidateResume.getSize() > maxSizeInBytes) {
	        return "File size must be less than or equal to 2MB";
	    }

	    // Proceed with mapping the DTO to the entity
	    Candidate candidate = new Candidate();
	   
	    MRFCandidate mrfCandidate = vendorDao.findMRFCandidateById(candidateDto.getSourceId());
	    
	    // Check if MRFCandidate is found
	    if (mrfCandidate == null) {
	        return "MRFCandidate with ID " + candidateDto.getSourceId() + " not found.";
	    }
	    
	    // Map DTO to Candidate entity
	    candidate.setFirstName(candidateDto.getFirstName());
	    candidate.setLastName(candidateDto.getLastName());
	    candidate.setMobileNumber(candidateDto.getMobileNumber());
	    candidate.setEmail(candidateDto.getEmail());
	    candidate.setExperience(candidateDto.getExperience());
	    candidate.setResume(candidateDto.getResume());
	    candidate.setSource("VENDOR");
	    candidate.setSourceId(candidateDto.getSourceId());
	    candidate.setSkill(candidateDto.getSkill());
	    candidate.setLocation(candidateDto.getLocation());
	    candidate.setPanNumber(candidateDto.getPanNumber());
	    candidate.setStatus(candidateDto.getStatus());
	    candidate.setAssignedAt(candidateDto.getAssignedAt());
	    candidate.setMrfCandidate(mrfCandidate);

	    // Pass the candidate entity and the resume file to the DAO
	    return vendorDao.addCandidate(candidate, candidateResume);
	}
	
	@Override
	 public List<String> getCandidateCountBySourceAndYear(Long sourceId, int year) {
	        // Fetch data from DAO
	        List<Object[]> results = vendorDao.getCandidateCountBySourceAndYear(sourceId, year);

	        // Prepare the formatted response
	        List<String> formattedResults = new ArrayList<>();
	        for (Object[] result : results) {
	            String month = (String) result[0];  // Month name
	            Long count = (Long) result[1];  // Count of candidates for that month
	            formattedResults.add("[month:" + month + ", Count: " + count + "]");
	        }

	        return formattedResults;
	    }
	
	@Override
    public void saveAllCandidates(List<CandidateDto> candidateDTOs) {
        List<Candidate> candidates = candidateDTOs.stream()
                .map(this::convertToEntity)  // Convert each DTO to an entity
                .collect(Collectors.toList());

        vendorDao.saveAll(candidates); // Persist the list of candidates
    }

    // Convert DTO to Entity
    private Candidate convertToEntity(CandidateDto candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setMobileNumber(candidateDTO.getMobileNumber());
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setExperience(candidateDTO.getExperience());
        candidate.setResume(candidateDTO.getResume());
        candidate.setSource(candidateDTO.getSource());
        candidate.setSourceId(candidateDTO.getSourceId());
        candidate.setSkill(candidateDTO.getSkill());
        candidate.setLocation(candidateDTO.getLocation());
        candidate.setPanNumber(candidateDTO.getPanNumber());
        candidate.setStatus(candidateDTO.getStatus());

        // Handle file uploads (convert MultipartFile to byte[] if needed)
        try {
            if (candidateDTO.getCandidateResume() != null) {
                candidate.setCandidateResume(candidateDTO.getCandidateResume().getBytes());
            }
        } catch (IOException e) {
            // Handle file conversion error if needed
        }

        candidate.setAssignedAt(candidateDTO.getAssignedAt());

        // Handle relationships, e.g., MRFCandidate
        // candidate.setMrfCandidate(someMrfCandidate); // Set if required, fetch the MRFCandidate as needed

        return candidate;
    }


}
