package com.rts.tap.daoimplementation;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.Employee.EmploymentStatus;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.service.EmailService1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	EntityManager eManager;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
    EmailService1 emailService;
	
	@Autowired
	LoginCredentialsDao loginCredentialsDao;

	@Override
	public void save(Employee employee) {

		Employee newEmployee = new Employee();

		newEmployee.setEmployeeEmail(employee.getEmployeeEmail());
		newEmployee.setManager(getEmployeeById(employee.getManager().getEmployeeId()));
		newEmployee.setRole(roleDao.getRoleById(employee.getRole().getRoleId()));
		newEmployee.setEmployeeStatus(EmploymentStatus.ACTIVE);
		
		LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUserEmail(employee.getEmployeeEmail());
        String password = generateRandomPassword(8); // Generate a random password
        loginCredentials.setPasswordHash(password); // Save the password hash
        loginCredentials.setCreatedDate(LocalDateTime.now());
        loginCredentials.setUpdatedDate(LocalDateTime.now());
        loginCredentials.setEmployee(newEmployee); // Set the employee reference

        loginCredentialsDao.save(loginCredentials);
        
        emailService.sendLoginCredentials(employee.getEmployeeEmail(), employee.getEmployeeEmail(), password);

		eManager.persist(newEmployee);
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
		String hql = "from Employee";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}
	

	public void updateEmployee(Employee employee) {
		eManager.merge(employee);
	}

	
	public Employee getEmployeeById(Long id) {
		return eManager.find(Employee.class, id);
	}

}
