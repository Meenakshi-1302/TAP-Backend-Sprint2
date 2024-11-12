package com.rts.tap.serviceimplementation;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.service.EmailService1;
import com.rts.tap.service.EmployeeService;



@Service
public class EmployeeServiceImpl1 implements EmployeeService {

    @Autowired
    EmployeeDao repo;
    @Autowired
    LoginCredentialsDao loginCredentialsDao;
    @Autowired
    EmailService1 emailService;

//	@Override
//	public void addEmployee(Employee employeee) {
//		repo.save(employeee);
//	}
    public void addEmployee(Employee employee) {
        // Save employee first
    	
    	
        repo.save(employee);

        // Create LoginCredentials for the employee
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUserEmail(employee.getEmployeeEmail());
        String password = generateRandomPassword(8); // Generate a random password
        loginCredentials.setPasswordHash(password); // Save the password hash
        loginCredentials.setCreatedDate(LocalDateTime.now());
        loginCredentials.setUpdatedDate(LocalDateTime.now());
        loginCredentials.setEmployee(employee); // Set the employee reference

        // Save login credentials
        loginCredentialsDao.save(loginCredentials);

        // Send email with login credentials
        emailService.sendLoginCredentials(employee.getEmployeeEmail(), employee.getEmployeeEmail(), password);
    }

		private String generateRandomPassword(int length) {
	    // Define the characters to be used in the password
	    String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    String specialCharacters = "!@#$%^&*()-_=+<>?";
	    
	    // Combine all characters
	    String allCharacters = upperCase + lowerCase + numbers + specialCharacters;

	    // Use SecureRandom for better randomness
	    SecureRandom random = new SecureRandom();
	    StringBuilder password = new StringBuilder(length);

	    // Ensure that the password contains at least one upper case, one lower case, one number, and one special character
	    password.append(upperCase.charAt(random.nextInt(upperCase.length())));
	    password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
	    password.append(numbers.charAt(random.nextInt(numbers.length())));
	    password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

	    // Fill the rest of the password length with random characters
	    for (int i = 4; i < length; i++) {
	        password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
	    }

	    // Shuffle the password to avoid any predictable patterns
	    return shuffleString(password.toString());
	}

	// Helper method to shuffle the characters in the password
	private String shuffleString(String input) {
	    char[] characters = input.toCharArray();
	    SecureRandom random = new SecureRandom();
	    
	    for (int i = 0; i < characters.length; i++) {
	        int randomIndex = random.nextInt(characters.length);
	        // Swap characters
	        char temp = characters[i];
	        characters[i] = characters[randomIndex];
	        characters[randomIndex] = temp;
	    }
	    
	    return new String(characters);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return repo.getAllEmployee();
	}

	@Override
	public void updateEmployee(Employee employee) {
		repo.updateEmployee(employee);
		
	}

	@Override
	public void addLoginCredentials(LoginCredentials loginCredentials) {
        // Save login credentials; employee is already set in the object
		loginCredentialsDao.save(loginCredentials);
    }

	@Override
	public Employee getEmployeeById(Long employeeId) {
		return repo.getEmployeeById(employeeId);
	}
	
	

}
