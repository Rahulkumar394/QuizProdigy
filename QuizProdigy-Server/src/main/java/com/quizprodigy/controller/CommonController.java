package com.quizprodigy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizprodigy.entity.Users;
import com.quizprodigy.jwtsecurity.JwtHelper;
import com.quizprodigy.request.LoginRequest;
import com.quizprodigy.response.LoginResponse;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.UserService;

@RestController
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuthenticationManager manager;

    // API endpoint for user registration with validation
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody Users user) {

        Response registerResponse = new Response();
        boolean isRegister = userService.registerUser(user);

        if (isRegister) {
            registerResponse.setResponseStatus("User Register Successful");
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        } else {
            registerResponse.setResponseStatus("User Already Registered");
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        }
    }

    // API endpoint for user login with JWT token generation
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        this.doAuthenticate(loginRequest.getUserId(), loginRequest.getPassword());
        LoginResponse loginResponse = userService.loginUser(loginRequest);

        if (loginResponse != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserId());
            String token = this.helper.generateToken(userDetails);

            loginResponse.setJwtToken(token);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // Helper method to authenticate user credentials
    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
                password);
        try {
            manager.authenticate(authenticationToken);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
