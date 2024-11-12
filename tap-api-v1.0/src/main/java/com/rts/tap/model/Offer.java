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
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offerId;

	@OneToOne
	private Candidate candidate;

	private Double offerPackage;

	private String candidateReason;

	private String candidateStatus;
	
	@ManyToOne
	private MRF mrf;

}
