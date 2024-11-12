package com.rts.tap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class OfferApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offerApprovalId;

	@ManyToOne
	private Offer offer;

	@OneToOne
	private ApproverLevel approverLevel;

	private String status;

	private String Reason;

}
