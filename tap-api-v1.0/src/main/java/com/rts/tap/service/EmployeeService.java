package com.rts.tap.service;


import java.util.List;

import com.rts.tap.model.Employee;
import com.rts.tap.model.LoginCredentials;

public interface EmployeeService {
	
	void addEmployee(Employee employeee);
	List<Employee> getAllEmployee();
	void updateEmployee(Employee employee);
	Employee getEmployeeById(Long employeeId);
	
		void addLoginCredentials(LoginCredentials loginCredentials);
     
}
