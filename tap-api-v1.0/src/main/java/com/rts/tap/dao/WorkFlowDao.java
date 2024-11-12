package com.rts.tap.dao;

import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;

public interface WorkFlowDao {
	public void addWorkFlow(WorkFlow workFlow);

	public WorkFlow findWorkFlowByMrf(MRF mrf);

	public void updateWorkFlow(WorkFlow workFlow);

	public void deleteWorkFlowById(Long workflowId);

	public WorkFlow findWorkFlowForRecruitmentProcessByMrf(MRF mrf);
	
	public WorkFlow getWorkFlowByMrfId(Long mrfId);

	public Employee getEmployeeByEmployeeId(Long employeeId);
}
