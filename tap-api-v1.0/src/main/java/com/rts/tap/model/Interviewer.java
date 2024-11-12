package com.rts.tap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Interviewer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interviewerId;

	@ManyToOne
	private Employee employee;

	private String status;

	public Interviewer() {
		super();
	}

	public Interviewer(Long interviewerId, Employee employee, String status) {
		super();
		this.interviewerId = interviewerId;
		this.employee = employee;
		this.status = status;
	}

	public Long getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(Long interviewerId) {
		this.interviewerId = interviewerId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
