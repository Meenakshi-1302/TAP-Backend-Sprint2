package com.rts.tap.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class MRFCandidate  {

//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mrfCandidateId;

	@OneToMany(targetEntity = Candidate.class)
	@JoinColumn(name = "mrfId", referencedColumnName = "mrfCandidateId")
	private List<Candidate> candidate;

	@ManyToOne
	private MRFRecruiters mrfRecruiter;

	private String Status;
	

	public MRFCandidate() {
		super();
	}

	public Long getMrfCandidateId() {
		return mrfCandidateId;
	}

	public void setMrfCandidateId(Long mrfCandidateId) {
		this.mrfCandidateId = mrfCandidateId;
	}

	public List<Candidate> getCandidate() {
		return candidate;
	}

	public void setCandidate(List<Candidate> candidate) {
		this.candidate = candidate;
	}

	public MRFRecruiters getMrfRecruiter() {
		return mrfRecruiter;
	}

	public void setMrfRecruiter(MRFRecruiters mrfRecruiter) {
		this.mrfRecruiter = mrfRecruiter;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
