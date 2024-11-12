package com.rts.tap.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long candidateId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String mobileNumber;

	@Column
	private String email;

	@Column
	private int experience;

	@Column
	private String resume;

	@Column
	private String source;

	@Column
	private long sourceId;

	@Column
	private String skill;

	@Column
	private String location;

	@Column
	private String panNumber;

	@Column(nullable = false)
	private String status;

	@Column
	private String password;

	@Column
	private Boolean isPasswordChanged;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CandiateDocument> documents;
	
	@Lob
	@Column(length = 1000000000)
	private byte[] candidateResume;
	
	@Column
	private LocalDateTime assignedAt;
	
	@ManyToOne
    @JoinColumn(name = "mrf_candidate_id")  // Name of the foreign key column in the candidate table
    private MRFCandidate mrfCandidate;

	public Candidate() {
		super();
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsPasswordChanged() {
		return isPasswordChanged;
	}

	public void setIsPasswordChanged(Boolean isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}

	public List<CandiateDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<CandiateDocument> documents) {
		this.documents = documents;
	}
	

	public long getSourceId() {
		return sourceId;
	}

	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	public byte[] getCandidateResume() {
		return candidateResume;
	}

	public void setCandidateResume(byte[] candidateResume) {
		this.candidateResume = candidateResume;
	}
	

	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}

	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}
	

	public MRFCandidate getMrfCandidate() {
		return mrfCandidate;
	}

	public void setMrfCandidate(MRFCandidate mrfCandidate) {
		this.mrfCandidate = mrfCandidate;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", experience=" + experience + ", resume="
				+ resume + ", source=" + source + ", sourceId=" + sourceId + ", skill=" + skill + ", location="
				+ location + ", panNumber=" + panNumber + ", status=" + status + ", password=" + password
				+ ", isPasswordChanged=" + isPasswordChanged + ", documents=" + documents + ", candidateResume="
				+ Arrays.toString(candidateResume) + ", assignedAt=" + assignedAt + "]";
	}

	

	

}
