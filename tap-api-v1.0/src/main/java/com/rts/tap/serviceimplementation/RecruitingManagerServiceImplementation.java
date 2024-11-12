package com.rts.tap.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.RecruitingManagerDao;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.service.RecruitingManagerService;

import jakarta.transaction.Transactional;


/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 05-11-2024
**/


@Service
@Transactional
public class RecruitingManagerServiceImplementation implements RecruitingManagerService {

	RecruitingManagerDao recruitingManagerDao;

	public RecruitingManagerServiceImplementation(RecruitingManagerDao recruitingManagerDao) {
		super();
		this.recruitingManagerDao = recruitingManagerDao;
	}

	@Override
	public Employee fetchRecruitingManagerById(long id) {
		return recruitingManagerDao.getRecruitingManagerById(id);
	}

	@Override
	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id) {
		return recruitingManagerDao.getAllMrfsAssignedForRM(id);
	}

	@Override
	public MRF getMrfById(long id) {
		return recruitingManagerDao.getMrfById(id);
	}

	@Override
	public String mrfAssignToVendor(MRFVendorDto mrfVendorDto) {
		return recruitingManagerDao.assignMrfToVendor(mrfVendorDto);
	}

	@Override
	public List<MRFVendorDto> getAllMrfVendorsRecords() {
		return recruitingManagerDao.getAllMrfsVendors().stream().map(mrfVendor -> {
			MRFVendorDto mrfVendorDto = new MRFVendorDto();
			mrfVendorDto.setMrfId(mrfVendor.getMrf().getMrfId());
			mrfVendorDto.setRecrutingManagerId(mrfVendor.getRecruitingManager().getEmployeeId());
			mrfVendorDto.setVendorId(mrfVendor.getVendor().getVendorId());
			System.out.println(mrfVendor.getVendorAssignedStatus());
			mrfVendorDto.setVendorAssignedStatus(mrfVendor.getVendorAssignedStatus());
			return mrfVendorDto;
		}).collect(Collectors.toList());
	}
}
