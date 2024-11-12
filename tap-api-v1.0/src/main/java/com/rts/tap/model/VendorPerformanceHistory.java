package com.rts.tap.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendor_performance_history")
public class VendorPerformanceHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long VendorPerformanceHistoryId;

	@OneToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@Column(name = "time_to_fill")
	private String timeToFill;

	@Column(name = "assigned_date")
	private LocalDateTime assignedDate;

	@Column(name = "closed_date")
	private LocalDateTime closedDate;

	@Column
	private LocalDateTime updatedDate;

	@Column(name = "offer_acceptance_rate")
	private double offerAcceptanceRate;

	@Column(name = "collective_score")
	private double collectiveScore;

	private String grade;

}