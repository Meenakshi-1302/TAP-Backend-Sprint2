package com.rts.tap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.LoginResponse;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.service.LoginCredentialsService;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class LoginCredentialsController {
	
    private final LoginCredentialsService loginCredentialsService;

    public LoginCredentialsController(LoginCredentialsService loginCredentialsService) {
        this.loginCredentialsService = loginCredentialsService;
    }

    @PostMapping(APIConstants.CREATE_LOGIN_URL)
    public ResponseEntity<LoginCredentials> create(@RequestBody LoginCredentials loginCredentials) {
        LoginCredentials created = loginCredentialsService.create(loginCredentials);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping(APIConstants.CHECK_LOGIN_CREDENTIALS_URL)
    public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginCredentials loginCredentials) {
        try {
            String email = loginCredentials.getUserEmail();
            String password = loginCredentials.getPasswordHash();

//            System.out.println("Attempting to log in user: " + email);

            LoginResponse response = loginCredentialsService.authenticate(email, password);

            if (response != null) {
            	if(response.getRole().equals("Admin")) {
            		loginCredentialsService.generateAndSendOtp(email);            		
            	}
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(APIConstants.VERIFY_OTP_URL)
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        try {
            boolean isValid = loginCredentialsService.verifyOtp(email, otp);
            if (isValid) {
                return ResponseEntity.ok(MessageConstants.OTP_VERIFY);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageConstants.FAILURE_MESSAGE);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageConstants.FAILURE_MESSAGE);
        }
    }

    @PostMapping(APIConstants.RESEND_OTP_URL)
    public ResponseEntity<String> resendOtp(@RequestParam String email) {
        try {
            loginCredentialsService.generateAndSendOtp(email);
            return ResponseEntity.ok(MessageConstants.OTP_RESENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageConstants.FAILURE_MESSAGE);
        }
    }
    
    
}
