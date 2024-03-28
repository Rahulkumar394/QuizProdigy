package com.quizprodigy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.quizprodigy.common.Validation;
import com.quizprodigy.entity.Students;
import com.quizprodigy.entity.Users;
import com.quizprodigy.repository.StudentRepository;
import com.quizprodigy.repository.UserRepository;

public class StudentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean addStudent(Students students) {

        Users findUser = userRepository.findByuserId(students.getStudentId());

        // Check if the user does not already exist
        if (findUser == null) {

            // Validate user input
            if (Validation.emailValidation(students.getStudentId())
                    && Validation.contactNumberValidation(students.getContactNo())
                    && Validation.nameValidation(students.getStudentName())
                    && Validation.passwordValidation(students.getPassword())) {

                // Set default values and encode the password
                students.setDeleted(false);
                students.setPassword(passwordEncoder.encode(students.getPassword()));

                // Set creation and modification dates
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                students.setCreatedDate(sqlDate);
                students.setModifyDate(sqlDate);
                students.setStatus("pending");

                // Create users entity object and save it to the database
                Users users = new Users();
                users.setUserId(students.getStudentId());
                users.setUserName(students.getStudentName());
                users.setRole("teacher");
                users.setPassword(students.getPassword());

                // Save both entities in a transaction
                students = studentRepository.save(students);
                users = userRepository.save(users);

                return true; // Successfully saved both entities
            }
        }
        return false; // User already exists or validation failed
    }
}
