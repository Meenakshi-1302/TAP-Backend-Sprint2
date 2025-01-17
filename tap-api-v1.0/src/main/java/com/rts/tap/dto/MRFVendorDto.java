package com.rts.tap.dto;

/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 04-11-2024
**/

public class MRFVendorDto {

	private long mrfId;
	private long vendorId;
	private long recrutingManagerId;
	private String vendorAssignedStatus;

	public MRFVendorDto() {
		super();
	}

	public long getMrfId() {
		return mrfId;
	}

	public void setMrfId(long mrfId) {
		this.mrfId = mrfId;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public long getRecrutingManagerId() {
		return recrutingManagerId;
	}

	public void setRecrutingManagerId(long recrutingManagerId) {
		this.recrutingManagerId = recrutingManagerId;
	}

	public String getVendorAssignedStatus() {
		return vendorAssignedStatus;
	}

	public void setVendorAssignedStatus(String vendorAssignedStatus) {
		this.vendorAssignedStatus = vendorAssignedStatus;
	}
}