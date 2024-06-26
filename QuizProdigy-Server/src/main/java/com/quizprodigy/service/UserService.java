package com.quizprodigy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		Users findUser = userRepository.findByuserId(user.getUserId());

		// Check if the user does not already exist
		// if (findUser == null) {

		// 	// Validate user input
		// 	if (Validation.emailValidation(user.getUserId())
		// 			&& Validation.contactNumberValidation(user.getContactNumber())
		// 			&& Validation.firstNameValidation(user.getFirstName())
		// 			&& Validation.lastNameValidation(user.getLastName())
		// 			&& Validation.passwordValidation(user.getPassword())) {

		// 		// Set default values and encode the password
		// 		user.setDeleted(false);
		// 		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// 		// Set creation and modification dates
		// 		java.util.Date utilDate = new java.util.Date();
		// 		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		// 		user.setCreatedDate(sqlDate);
		// 		user.setModifyDate(sqlDate);
		// 		user.setStatus("pending");

		// 		// Save the user in the database
		// 		findUser = userRepository.save(user);
		// 		return true;
		// 	}
		// }
		// return false; // User already exists or validation failed
		return false;
	}

	// Login user and return login response
	public LoginResponse loginUser(LoginRequest loginRequest) {

	// 	Users findUser = userRepository.findByUserId(loginRequest.getUserId());
	// 	LoginResponse loginResponse = new LoginResponse();

	// 	// Check if the user exists
	// 	if (findUser != null) {
	// 		// Populate login response
	// 		loginResponse.setName(findUser.getFirstName() + " " + findUser.getLastName());
	// 		loginResponse.setUserId(findUser.getUserId());
	// 		loginResponse.setRole(findUser.getRole());
	// 		return loginResponse;
	// 	}

	// 	return null; // User not found
	// }
	return null;
	}
}
