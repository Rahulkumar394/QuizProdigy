package com.quizprodigy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizprodigy.entity.Students;
import com.quizprodigy.entity.Teachers;
import com.quizprodigy.service.StudentService;
import com.quizprodigy.service.TeacherService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TeacherService  teacherService;
    @Autowired
    private StudentService studentService;

    // Through this API we get all Teachers whose status is "Pending" (account is not activated yet)
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pending-teacher")
	@Secured("admin")
	public ResponseEntity<List<Teachers>> getAllPendingTeachers() {

		System.out.println("<=====>AdminController  getAllPendingTeachers()=====>");
		List<Teachers> teachers=teacherService.getTeachersByStatus("Pending") ;
        if (teachers == null)
			return ResponseEntity.status(HttpStatus.OK).body(teachers);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teachers);
	}

    // Through this API we get all Students whose status is "Pending" (account is not activated yet)
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pending-student")
	@Secured("admin")
	public ResponseEntity<List<Students>> getAllPendingStudents() {

		System.out.println("<=====>AdminController  getAllPendingStudents()=====>");
		List<Students> students=studentService.getStudentsByStatus("Pending") ;
        if (students == null)
			return ResponseEntity.status(HttpStatus.OK).body(students);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
	}

    // Through this API we get all Teachers whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pending-teacher")
	@Secured("admin")
	public ResponseEntity<List<Teachers>> getAllAcceptedTeachers() {

		System.out.println("<=====>AdminController  getAllPendingTeachers()=====>");
		List<Teachers> teachers=teacherService.getTeachersByStatus("Accepted") ;
        if (teachers == null)
			return ResponseEntity.status(HttpStatus.OK).body(teachers);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teachers);
	}

    // Through this API we get all Students whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pending-student")
	@Secured("admin")
	public ResponseEntity<List<Students>> getAllAcceptedStudents() {

		System.out.println("<=====>AdminController  getAllPendingStudents()=====>");
		List<Students> students=studentService.getStudentsByStatus("Accepted") ;
        if (students == null)
			return ResponseEntity.status(HttpStatus.OK).body(students);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
	}

}
