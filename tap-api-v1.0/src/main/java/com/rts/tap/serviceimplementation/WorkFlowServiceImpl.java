package com.rts.tap.serviceimplementation;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.WorkFlowService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WorkFlowServiceImpl implements WorkFlowService {
	WorkFlowDao workFlowDao;
	MRFDao mrfDao;

	public WorkFlowServiceImpl(WorkFlowDao workFlowDao, MRFDao mrfDao) {
		super();
		this.workFlowDao = workFlowDao;
		this.mrfDao = mrfDao;
	}

	@Override
	public WorkFlow getWorkflowByMrfIdForRecruitmentProcess(Long mrfId) {
		MRF mrf = mrfDao.findById(mrfId);
		return workFlowDao.findWorkFlowForRecruitmentProcessByMrf(mrf);
	}
}
