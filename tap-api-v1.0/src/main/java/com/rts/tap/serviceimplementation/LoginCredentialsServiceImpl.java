package com.rts.tap.serviceimplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.daoimplementation.AdminDaoImpl;
import com.rts.tap.daoimplementation.LoginCredentialsDaoImpl;
import com.rts.tap.dto.LoginResponse;
import com.rts.tap.model.Admin;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.service.EmailService1;
import com.rts.tap.service.LoginCredentialsService;


@Service
public class LoginCredentialsServiceImpl implements LoginCredentialsService {

    private final LoginCredentialsDao loginCredentialsDao;
    
    @Autowired
    private AdminDaoImpl adminDaoImpl;
    
    @Autowired
    private LoginCredentialsDaoImpl loginCredentialsDaoImpl;
    
    @Autowired
    private EmailService1 emailService;
    
    private Map<String, String> otpStorage = new HashMap<>(); // Store OTPs temporarily
    private Map<String, Long> otpExpiry = new HashMap<>(); // Store OTP expiration times


    public LoginCredentialsServiceImpl(LoginCredentialsDao loginCredentialsDao) {
        this.loginCredentialsDao = loginCredentialsDao;
    }

    @Override
    public LoginCredentials create(LoginCredentials loginCredentials) {
        return loginCredentialsDao.save(loginCredentials);
    }

	@Override
	public LoginResponse authenticate(String email, String password) {
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    	Admin admin = adminDaoImpl.findEmail(email);
        LoginCredentials loginCredentials = loginCredentialsDaoImpl.findEmail(email);
        

        if (admin != null && admin.getPasswordHash().equals(password)) {
        	Long Aid = admin.getAdminId();
            return new LoginResponse(Aid,"Success","Admin");
        }
        
        else {
//        	if(loginCredentials!=null && loginCredentials.matches(password,loginCredentials.getPasswordHash())) {
        	if(loginCredentials!=null && loginCredentials.getPasswordHash().equals(password)) {	
        		Long userId = loginCredentials.getUserId();
	   			System.out.println("Checking Process!!!");
        		String role = loginCredentialsDaoImpl.getRole(userId);
	   			System.out.println(role);

        		return new LoginResponse(userId,"Success",role);        		
        	}else {
        		return new LoginResponse("Fail","Unknown");
        	}
        }

//        else if(parent != null ) {
//    		
//        	if(bcrypt.matches(password,parent.getPassword())) {
//        		int Pid = parent.getParentId();
//        		return new LoginResponse(Pid,"Success","Parent");        		
//        	} else {
//        		return new LoginResponse(9999,"Fail","Unknown");
//        	}
//        	
//        } else {
//        	return new LoginResponse(9999,"Fail","Unknown");        	
//        }
	}

	@Override
	public void generateAndSendOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(9999)); // Generate a random 6-digit OTP
        otpStorage.put(email, otp);
        otpExpiry.put(email, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)); // Set expiry for 5 minutes

        // Use EmailService to send the OTP
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otp + ". It is valid for 5 minutes.";
        emailService.sendEmail(email, subject, body); // Send OTP via email
    }
	@Override
    public boolean verifyOtp(String email, String submittedOtp) {
        Long expiryTime = otpExpiry.get(email);
        if (expiryTime != null && System.currentTimeMillis() < expiryTime) {
            String storedOtp = otpStorage.get(email);
            if (storedOtp != null && storedOtp.equals(submittedOtp)) {
                // OTP is valid, remove it from storage
                otpStorage.remove(email);
                otpExpiry.remove(email);
                return true; // OTP verified successfully
            }
        }
        return false; // OTP is invalid or expired
    }

   
}

