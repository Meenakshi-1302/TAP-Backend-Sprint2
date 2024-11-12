package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.dto.ApproverLevelDto;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.ApproverLevelService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ApproverLevelSeviceImpl implements ApproverLevelService {
	
	private ApproverLevelDao approverLevelDao;
	private WorkFlowDao workflowDao;
    private EmployeeDao employeeDao;
    private MRFDao mrfDao;
   
	public ApproverLevelSeviceImpl(ApproverLevelDao approverLevelDao, WorkFlowDao workflowDao, EmployeeDao employeeDao,
			MRFDao mrfDao) {
		super();
		this.approverLevelDao = approverLevelDao;
		this.workflowDao = workflowDao;
		this.employeeDao = employeeDao;
		this.mrfDao = mrfDao;
	}
	
	@Override
	public void saveApproverLevel(List<ApproverLevel> approverLevel) {
	    approverLevelDao.saveApproverLevel(approverLevel);
	    
	    WorkFlow workFlow = new WorkFlow();
	    
	    ApproverLevelDto approverLevelDto = new ApproverLevelDto();
	    approverLevelDto.setApproverLevel(approverLevel);
	    workFlow.setCount(approverLevelDto.getApproverLevel().size());
	    workFlow.setWorkFlowType(MessageConstants.SET_WORKFLOW_TYPE1);
	    workFlow.setStatus(MessageConstants.SET_WORKFLOW_STATUS);
	    
	    if (approverLevelDto.getApproverLevel() != null && !approverLevelDto.getApproverLevel().isEmpty()) {
	        Long mrfId = approverLevelDto.getApproverLevel().get(0).getMrf().getMrfId();
	        if (mrfId != null) {
	            MRF mrf = mrfDao.findById(mrfId);
	            workFlow.setMrf(mrf);
	        } else {
	            throw new IllegalArgumentException("MRF ID cannot be null");
	        }
	        
	        Long buHeadId = approverLevelDto.getBuHeadId();
	        if (buHeadId != null) {
	            Employee buHead = employeeDao.getEmployeeById(buHeadId);
	            workFlow.setEmployee(buHead);
	        } else {
	            throw new IllegalArgumentException("BU Head ID cannot be null");
	        }
	        
	        workflowDao.addWorkFlow(workFlow);
	    } else {
	        throw new IllegalArgumentException("Approver levels cannot be null or empty");
	    }
	}
	
	@Override
	public void updateApproverLevel(ApproverLevel approverLevel) {
	    approverLevelDao.updateApproverLevel(approverLevel);
	}

	@Override
	public void deleteApproverLevel(long approverLevelId) {
		ApproverLevel approverLevel = approverLevelDao.findApproverLevelById(approverLevelId);
		List<ApproverLevel> approverLevels = approverLevelDao.getApproverLevelByMrfId(approverLevel.getMrf().getMrfId());
     	for (ApproverLevel al : approverLevels) {
			if(al.getLevel() > approverLevel.getLevel()) {
				al.setLevel(al.getLevel() - 1);
				approverLevelDao.updateApproverLevel(al);
			}
	   }
		approverLevelDao.deleteApproverLevel(approverLevelId);
		
		WorkFlow workFlow = workflowDao.findWorkFlowByMrf(approverLevel.getMrf());
		workFlow.setCount(workFlow.getCount() - 1);
		workflowDao.updateWorkFlow(workFlow);
	  }

	@Override
	public List<ApproverLevel> getApproverLevelByMrfId(Long mrfId) {   
		return approverLevelDao. getApproverLevelByMrfId(mrfId);
	}
	
	@Override
	public WorkFlow getWorkFlowByMrfId(Long mrfId) {   
		return workflowDao.getWorkFlowByMrfId(mrfId);
	}

	@Override
	public Employee getEmployeeByEmployeeId(Long employeeId) {   
		return workflowDao.getEmployeeByEmployeeId(employeeId);
	}

}
