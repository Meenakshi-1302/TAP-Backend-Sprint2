package com.rts.tap.service;

import java.util.List;

import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruitingManager;

/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 05-11-2024
**/

public interface RecruitingManagerService {
	
	public Employee fetchRecruitingManagerById(long id);

	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id);

	public MRF getMrfById(long id);
	
	public String mrfAssignToVendor(MRFVendorDto mrfVendorDto);
	
	public List<MRFVendorDto> getAllMrfVendorsRecords(); 

}
