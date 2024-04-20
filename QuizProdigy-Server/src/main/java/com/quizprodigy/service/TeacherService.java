package com.quizprodigy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizprodigy.common.Validation;
import com.quizprodigy.entity.Teachers;
import com.quizprodigy.entity.Users;
import com.quizprodigy.repository.TeacherRepository;
import com.quizprodigy.repository.UserRepository;

@Service
public class TeacherService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    // Through this method , we can add new teachers to the database in teachers
    // table and users table
    @Transactional
    public boolean addTeacher(Teachers teachers) {

        Users findUser = userRepository.findByUserId(teachers.getTeacherId());

        // Check if the user does not already exist
        if (findUser == null) {

            // Validate user input
            if (Validation.emailValidation(teachers.getTeacherId())
                    && Validation.contactNumberValidation(teachers.getContactNo())
                    && Validation.nameValidation(teachers.getTeacherName())
                    && Validation.passwordValidation(teachers.getPassword())) {

                // Set default values and encode the password
                teachers.setDeleted(false);
                teachers.setPassword(passwordEncoder.encode(teachers.getPassword()));

                // Set creation and modification dates
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                teachers.setCreatedDate(sqlDate);
                teachers.setModifyDate(sqlDate);
                teachers.setStatus("Pending");

                // Create users entity object and save it to the database
                Users users = new Users();
                users.setUserId(teachers.getTeacherId());
                users.setName(teachers.getTeacherName());
                users.setRole("Teacher");
                users.setPassword(teachers.getPassword());

                // Save both entities in a transaction
                teachers = teacherRepository.save(teachers);
                users = userRepository.save(users);

                return true; // Successfully saved both entities
            }
        }
        return false; // User already exists or validation failed
    }

    // Getting all teaches whose status is  "pending"
    public List<Teachers> getTeachersByStatus(String status) {
        return teacherRepository.findAllTeachersByStatus(status);
    }

    // Through this method we can update status
	@Transactional
	public void updateStatus(String email, String status) {
		teacherRepository.updateStatusById(email, status);
		return;
	}

    
}