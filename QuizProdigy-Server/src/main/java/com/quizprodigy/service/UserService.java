package com.quizprodigy.service;

import org.springframework.stereotype.Service;



@Service
public class UserService {

//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	// Registring new user in database
//	public boolean registerUser(Users user) {
//		Users findUser = userRepository.findByEmail(user.getEmail());
//
//		// if findUser is equal to null that means there is no such email in the
//		// database so we
//		if (findUser == null) {
//
//			// Write code for validation
//			if (Validation.emailValidation(user.getEmail())
//					&& Validation.contactNumberValidation(user.getContactNumber())
//					&& Validation.firstNameValidation(user.getFirstName())
//					&& Validation.lastNameValidation(user.getLastName())
//					&& Validation.passwordValidation(user.getPassword())) {
//
//				// Setting default values
//				user.setUserId(UUID.randomUUID().toString());
//				user.setIsDeleted(false);
//				user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//				// Create a java.util.Date object
//				java.util.Date utilDate = new java.util.Date();
//
//				// Convert java.util.Date to java.sql.Date
//				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//
//				user.setCreatedDate(sqlDate);
//				user.setModifyDate(sqlDate);
//
//				// Save in the database
//				findUser = userRepository.save(user);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public LoginResponse loginUser(LoginRequest loginRequest) {
//
//		Users findUser1 = userRepository.findByEmail(loginRequest.getEmail());
//		LoginResponse loginResponse = new LoginResponse();
//
//		// user finding loop
//		if (findUser1 != null) {
//			loginResponse.setName(findUser1.getFirstName() + " " + findUser1.getLastName());
//			loginResponse.setUserId(findUser1.getUserId());
//			loginResponse.setRole(findUser1.getRole());
//			return loginResponse;
//			// password not match this else run
//		}
//		return null;
//	}
}
