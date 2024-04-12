package com.quizprodigy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;

	// Through this API we get all Teachers whose status is "Pending" (account is
	// not activated yet)
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pending-teacher")
	@Secured("admin")
	public ResponseEntity<List<Teachers>> getAllPendingTeachers() {

		System.out.println("<=====>AdminController  getAllPendingTeachers()=====>");
		List<Teachers> teachers = teacherService.getTeachersByStatus("Pending");
		if (teachers != null)
			return ResponseEntity.status(HttpStatus.OK).body(teachers);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teachers);
	}

	// Through this API we get all Teachers whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/accepted-teacher")
	@Secured("admin")
	public ResponseEntity<List<Teachers>> getAllAcceptedTeachers() {

		System.out.println("<=====>AdminController  getAllAcceptedTeachers()=====>");
		List<Teachers> teachers = teacherService.getTeachersByStatus("Accepted");
		if (teachers != null)
			return ResponseEntity.status(HttpStatus.OK).body(teachers);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teachers);
	}

	// Through this API we get all Teachers whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/accepted-student")
	@Secured("admin")
	public ResponseEntity<List<Students>> getAllAcceptedStudent() {

		System.out.println("<=====>AdminController  getAllAcceptedStudent()=====>");
		List<Students> students = studentService.getStudentsByStatus("Accepted");
		System.out.println("==========>" + students);
		return ResponseEntity.status(HttpStatus.OK).body(students);

	}

	// This method is used to accept the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@GetMapping("/approve-request/{email}")
	public ResponseEntity<?> approveRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController approveRequest()=====>" + email);
		teacherService.updateStatus(email, "Accepted");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// This method is used to reject the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@GetMapping("/reject-request/{email}")
	public ResponseEntity<?> rejectRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController  rejectRequest()=====>" + email);
		teacherService.updateStatus(email, "Rejected");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
