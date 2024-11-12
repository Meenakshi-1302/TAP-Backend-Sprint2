package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.service.RecruitingManagerService;

/**
 * author: Jeevarajan Rajarajacholan version: v1.0 updated at: 04-11-2024
 **/

@RestController
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(APIConstants.RECRUITING_MANAGER_URL)
public class RecruitingManagerController {

	RecruitingManagerService recruitingManagerService;

	public RecruitingManagerController(RecruitingManagerService recruitingManagerService) {
		super();
		this.recruitingManagerService = recruitingManagerService;
	}

	/**
	 * this api will accept MrfVendor dto and perform Assign operation and return
	 * message for output success or failed
	 * 
	 * @param(vendor id) - long
	 * @param(Mrf's Id) - long
	 * @param(Recruiting Manager's Id) - long
	 * @return String - successfully saved or not
	 **/
	@PostMapping(APIConstants.RECRUITING_MANAGER_ASSIGN_MRF_VENDOR)
	public ResponseEntity<String> doAssignMrfToVendor(@RequestBody MRFVendorDto mrfVendorDto) {
		return new ResponseEntity<>(recruitingManagerService.mrfAssignToVendor(mrfVendorDto), HttpStatus.OK);
	}

	/**
	 * this api will get all the mrfs that are assigned for specific Recruiting
	 * manager 
	 * @param(recruiting manager's Id) - long
	 * @return List of MrfRecruitingManager object
	 **/
	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_MRF)
	public List<MRFRecruitingManager> doGetAllMrfsAssignedForRM(@PathVariable long id) {
		return recruitingManagerService.getAllMrfsAssignedForRM(id);
	}
	
	/**
	 * this api will get all the mrfs that are assigned for specific vendors
	 * manager 
	 * @return List of MrfVendor object
	 **/
	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_MRFVENDOR)
	public ResponseEntity<List<MRFVendorDto>> doGetAllMrfsVendor() {
		try {
			return new ResponseEntity<>(recruitingManagerService.getAllMrfVendorsRecords(),HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);			
		}
	}

}
