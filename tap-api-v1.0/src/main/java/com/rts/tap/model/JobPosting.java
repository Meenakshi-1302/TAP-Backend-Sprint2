package com.rts.tap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobPostingId;

	private String jobDescription;

	private String jobTitle;

	@OneToOne
	private BasicJobTable basicJobTable;

	@OneToOne
	private MRF mrf;

	@Column(name = "JobPoster", nullable = true, length = 100000000)
	private byte[] poster;

	private List<String> jobType;

	private List<String> Shift;

	private String url;

}
