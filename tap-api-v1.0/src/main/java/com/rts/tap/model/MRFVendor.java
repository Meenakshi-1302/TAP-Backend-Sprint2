package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mrfVendor_table")
public class MRFVendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mrfVendorId;

	@ManyToOne
	@JoinColumn(name = "mrfId")
	private MRF mrf;

	@ManyToOne
	@JoinColumn(name = "recruitingManagerId")
	private Employee recruitingManager;
	
	@ManyToOne
	@JoinColumn(name = "vendorId")
	private Vendor vendor;

	@Column
	private String vendorAssignedStatus;

	public MRFVendor() {
		super();
	}

	public Long getMrfVendorId() {
		return mrfVendorId;
	}

	public void setMrfVendorId(Long mrfVendorId) {
		this.mrfVendorId = mrfVendorId;
	}

	public MRF getMrf() {
		return mrf;
	}

	public void setMrf(MRF mrf) {
		this.mrf = mrf;
	}

	public Employee getRecruitingManager() {
		return recruitingManager;
	}

	public void setRecruitingManager(Employee recruitingManager) {
		this.recruitingManager = recruitingManager;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getVendorAssignedStatus() {
		return vendorAssignedStatus;
	}

	public void setVendorAssignedStatus(String vendorAssignedStatus) {
		this.vendorAssignedStatus = vendorAssignedStatus;
	}
}
