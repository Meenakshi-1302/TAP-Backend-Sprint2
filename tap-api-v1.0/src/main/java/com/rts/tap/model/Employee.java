package com.rts.tap.model;
 
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "employees")
public class Employee {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;
 
	@Column(name = "employee_email", unique = true)
	private String employeeEmail;
 
	@Enumerated(EnumType.STRING)
	@Column(name = "employee_status")
	private EmploymentStatus employeeStatus;
 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporting_id")
	private Employee manager;
 
	public enum EmploymentStatus {
		ACTIVE, INACTIVE
	}
	@PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }
 
    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now(); // Only update updatedDate on update
    }
 
	public Long getEmployeeId() {
		return employeeId;
	}
 
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
 
	public String getEmployeeEmail() {
		return employeeEmail;
	}
 
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
 
	public EmploymentStatus getEmployeeStatus() {
		return employeeStatus;
	}
 
	public void setEmployeeStatus(EmploymentStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
 
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
 
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
 
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
 
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
 
	public Role getRole() {
		return role;
	}
 
	public void setRole(Role role) {
		this.role = role;
	}
 
	public Employee getManager() {
		return manager;
	}
 
	public void setManager(Employee manager) {
		this.manager = manager;
	}
 
	public Employee(Long employeeId, String employeeEmail, EmploymentStatus employeeStatus, LocalDateTime createdDate,
			LocalDateTime updatedDate, Role role, Employee manager) {
		super();
		this.employeeId = employeeId;
		this.employeeEmail = employeeEmail;
		this.employeeStatus = employeeStatus;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.role = role;
		this.manager = manager;
	}
 
	public Employee() {
		super();
	}
	
}
 