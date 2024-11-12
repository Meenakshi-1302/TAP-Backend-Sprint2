package com.rts.tap.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interviewId;

	private String interviewTitle;

	@ManyToOne
	private RecruitmentProcess recruitmentProcess;

	@ManyToOne
	private Candidate candidate;

	private LocalDate interviewDate;

	private LocalTime interviewFromTime;

	private LocalTime interviewToTime;

	private String meetingUrl;

	private String candidateStatus;

	private String Others;

}
