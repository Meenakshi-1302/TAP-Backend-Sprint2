package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendor_performance")
public class VendorPerformance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorPerformanceId;

	@OneToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@Column(name = "overall_performance")
	private String overallPerformance;

	private String grade;

}