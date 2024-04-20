package com.quizprodigy.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizprodigy.common.Validation;
import com.quizprodigy.dto.ExamQuestionDto;
import com.quizprodigy.entity.Students;
import com.quizprodigy.entity.Users;
import com.quizprodigy.repository.ExamRepository;
import com.quizprodigy.repository.StudentRepository;
import com.quizprodigy.repository.UserRepository;
import com.quizprodigy.response.GetQuestionAnswerResponse;

@Service
public class StudentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamService examService;

    @Transactional
    public boolean addStudent(Students students) {

        Users findUser = userRepository.findByUserId(students.getStudentId());

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
                students.setStatus("Pending");

                // Create users entity object and save it to the database
                Users users = new Users();
                users.setUserId(students.getStudentId());
                users.setName(students.getStudentName());
                users.setRole("Student");
                users.setPassword(students.getPassword());

                // Save both entities in a transaction
                students = studentRepository.save(students);
                users = userRepository.save(users);
                return true; // Successfully saved both entities
            }
        }
        return false; // User already exists or validation failed
    }

    // Getting all teaches whose status is "pending"
    public List<Students> getStudentsByStatus(String status) {
        return studentRepository.findAllStudentsByStatus(status);
    }

    // Through this method we can update status
    @Transactional
    public void updateStatus(String email, String status) {
        studentRepository.updateStatusByShopEmail(email, status);
        return;
    }

    // Getting question for taking exam
    public GetQuestionAnswerResponse takeExamById(String examId) {
        GetQuestionAnswerResponse question = examService.getExamDetailsById(examId);
        Collections.shuffle(question.getQuestions());
        return question;
    }

}
