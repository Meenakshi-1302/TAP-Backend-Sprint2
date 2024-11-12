package com.rts.tap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.WorkFlowService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class WorkFlowController {
	WorkFlowService workFlowService;
	
	public WorkFlowController(WorkFlowService workFlowService) {
		super();
		this.workFlowService = workFlowService;
	}

	@GetMapping(APIConstants.GET_WORKFLOW_FOR_RECRUITMENT_PROCESS)
	public ResponseEntity<WorkFlow> getWorkflowForRecruitmentProcess(@PathVariable Long mrfId) {
		try {
			WorkFlow workFlow = workFlowService.getWorkflowByMrfIdForRecruitmentProcess(mrfId);
			return ResponseEntity.ok(workFlow);
		} catch (Exception e) {
			return null;
		}
	}
}
