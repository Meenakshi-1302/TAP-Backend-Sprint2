package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Employee;

public interface EmployeeDao {

	void save(Employee employee);
	List<Employee> getAllEmployee();
	void updateEmployee(Employee employee);
	Employee getEmployeeById(Long employeeId);
}

