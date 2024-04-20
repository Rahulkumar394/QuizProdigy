package com.quizprodigy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizprodigy.common.Validation;
import com.quizprodigy.entity.Users;
import com.quizprodigy.repository.UserRepository;
import com.quizprodigy.request.LoginRequest;
import com.quizprodigy.response.LoginResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Registering a new user in the database
	public boolean registerUser(Users user) {
		Users findUser = userRepository.findByUserId(user.getUserId());

		// Check if the user does not already exist
		if (findUser != null) {

			// Validate user input
			if (Validation.emailValidation(user.getUserId())
					&& Validation.passwordValidation(user.getPassword())) {

				// Set default values and encode the password
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				// Save the user in the database
				findUser = userRepository.save(user);
				return true;
			}
		}
		return false; // User already exists or validation failed
	}

	// Login user and return login response
	public LoginResponse loginUser(LoginRequest loginRequest) {


		// Optional<Users> exitingUser = userRepository.findById(loginRequest.getUserId());
		// if (exitingUser.isPresent()) {
		// 	Users findUser = exitingUser.get();
		// 	System.out.println("/////////////////////" + findUser);
		// 	LoginResponse loginResponse = new LoginResponse();

		// 	// Check if the user exists
		// 	if (findUser != null) {
		// 		// Populate login response
		// 		loginResponse.setName(findUser.getUsername());
		// 		loginResponse.setUserId(findUser.getUserId());
		// 		loginResponse.setRole(findUser.getRole());
		// 		return loginResponse;
		// 	}
		// }


		Users findUser = userRepository.findByUserId(loginRequest.getUserId());
		System.out.println("/////////////////////" + findUser);
		LoginResponse loginResponse = new LoginResponse();
		

		// Check if the user exists
		if (findUser != null) {
			// Populate login response
			loginResponse.setName(findUser.getName());
			loginResponse.setUserId(findUser.getUserId());
			loginResponse.setRole(findUser.getRole());
			return loginResponse;
		}
		return null; // User not found
	}
}
