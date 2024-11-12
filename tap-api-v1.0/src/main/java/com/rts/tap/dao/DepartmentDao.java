package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Department;

public interface DepartmentDao {

	void save(Department department);
	List<Department> getAllDepartments();
	void update(Department department);
	
}

