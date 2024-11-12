package com.rts.tap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table

public class BasicJobTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long basicJobTableId;

	private String jobDescription;

	private String jobTitle;

	private Double jobPackage;

	private Double experience;

	private String Role;

}
