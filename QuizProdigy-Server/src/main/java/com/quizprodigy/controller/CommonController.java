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

import com.quizprodigy.entity.Students;
import com.quizprodigy.entity.Teachers;
import com.quizprodigy.jwtsecurity.JwtHelper;
import com.quizprodigy.request.LoginRequest;
import com.quizprodigy.response.LoginResponse;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.StudentService;
import com.quizprodigy.service.TeacherService;
import com.quizprodigy.service.UserService;

@RestController
public class CommonController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper helper;    
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    // API endpoint for Teacher registration with validation
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register-teacher")
    public ResponseEntity<Response> registerTeacher(@RequestBody Teachers teacher) {

        System.out.println("<=====Common controller RegisterTeacher()=====>\n"+teacher);

        Response registerResponse = new Response();
        boolean isRegister =teacherService.addTeacher(teacher);

        if (isRegister) {
            registerResponse.setResponseStatus("Register Successful");
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        } else {
            registerResponse.setResponseStatus("User Already Registered");
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        }
    }

    // API endpoint for Teacher registration with validation
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register-student")
    public ResponseEntity<Response> RegisterStudent(@RequestBody Students students) {

        System.out.println("<=====Common controller RegisterStudent()=====>\n"+students);
        Response registerResponse = new Response();
        boolean isRegister = studentService.addStudent(students);

        if (isRegister) {
            registerResponse.setResponseStatus("Register Successful");
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

        System.out.println("<=====Common controller login()=====>\n"+loginRequest);

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
