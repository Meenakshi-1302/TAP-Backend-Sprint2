package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mrfStatus_table")
public class MRFStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mrfStatusId;

	@Column
	private String mrfApprovalStatus;

	@Column
	private String descriptionForChanges;

	@Column
	private int requirementFilled;

	public MRFStatus() {
		super();
	}

	public MRFStatus(Long mrfStatusId, String mrfApprovalStatus, String descriptionForChanges, int requirementFilled) {
		super();
		this.mrfStatusId = mrfStatusId;
		this.mrfApprovalStatus = mrfApprovalStatus;
		this.descriptionForChanges = descriptionForChanges;
		this.requirementFilled = requirementFilled;
	}

	public Long getMrfStatusId() {
		return mrfStatusId;
	}

	public void setMrfStatusId(Long mrfStatusId) {
		this.mrfStatusId = mrfStatusId;
	}

	public String getMrfApprovalStatus() {
		return mrfApprovalStatus;
	}

	public void setMrfApprovalStatus(String mrfApprovalStatus) {
		this.mrfApprovalStatus = mrfApprovalStatus;
	}

	public String getDescriptionForChanges() {
		return descriptionForChanges;
	}

	public void setDescriptionForChanges(String descriptionForChanges) {
		this.descriptionForChanges = descriptionForChanges;
	}

	public int getRequirementFilled() {
		return requirementFilled;
	}

	public void setRequirementFilled(int requirementFilled) {
		this.requirementFilled = requirementFilled;
	}

	@Override
	public String toString() {
		return "MRFStatus [mrfStatusId=" + mrfStatusId + ", mrfApprovalStatus=" + mrfApprovalStatus
				+ ", descriptionForChanges=" + descriptionForChanges + ", requirementFilled=" + requirementFilled + "]";
	}

}
