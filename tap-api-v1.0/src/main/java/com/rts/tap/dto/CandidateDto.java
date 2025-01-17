package com.rts.tap.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.model.MRFCandidate;

public class CandidateDto {
	private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private int experience;
    private String resume;
    private String source;
    private long sourceId;
    private String skill;
    private String location;
    private String panNumber;
    private String status;
    private MultipartFile candidateResume; // Handle file uploads
    private LocalDateTime assignedAt;
    private MRFCandidate mrfCandidate;
    
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public long getSourceId() {
		return sourceId;
	}
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
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
	public MultipartFile getCandidateResume() {
		return candidateResume;
	}
	public void setCandidateResume(MultipartFile candidateResume) {
		this.candidateResume = candidateResume;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
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
	
	
	
    
    // Getters and setters for all fields
    

}
