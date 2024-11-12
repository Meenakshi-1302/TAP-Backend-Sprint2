package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFVendor;

/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 04-11-2024
**/

public interface RecruitingManagerDao {

	public Employee getRecruitingManagerById(long id);

	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id);

	public MRF getMrfById(long id);
	
	public String assignMrfToVendor(MRFVendorDto mrfVendorDto);
	
	public List<MRFVendor> getAllMrfsVendors();

}
