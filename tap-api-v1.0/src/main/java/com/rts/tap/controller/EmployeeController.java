package com.rts.tap.controller;

import java.util.List;

//import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Employee;
import com.rts.tap.model.Employee.EmploymentStatus;
import com.rts.tap.service.EmployeeService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)

public class EmployeeController {
	

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	

	@PostMapping(APIConstants.ADD_EMPLOYEE_URL)
    public String addEmployee(@RequestBody Employee employee) {
        try {
        	employee.setEmployeeStatus(EmploymentStatus.ACTIVE);
            employeeService.addEmployee(employee);
            return MessageConstants.SUCCESS_MESSAGE;
        } catch (Exception e) {
            return MessageConstants.FAILURE_MESSAGE;
        }
    }


	@GetMapping(APIConstants.GETALL_EMPLOYEE_URL)
	public List<Employee> viewAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@PutMapping(APIConstants.UPDATE_EMPLOYEE_URL)
	public String updateEmployee(@RequestBody Employee employee) {
		try {
			employeeService.updateEmployee(employee);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}

	}
	
	@GetMapping(APIConstants.GET_EMPLOYEE_BY_ID)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}


}
