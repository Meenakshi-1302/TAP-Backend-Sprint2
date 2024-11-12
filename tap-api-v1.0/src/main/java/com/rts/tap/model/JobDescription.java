package com.rts.tap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobDescription_table")
public class JobDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobDescriptionId;

	@Column
	private String jobTitle;

	@Column
	private List<String> jobParameterList;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] rolesAndResponsibilities;

}
