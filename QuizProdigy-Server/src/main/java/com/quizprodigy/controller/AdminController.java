package com.quizprodigy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/pending-teacher")
	@Secured("admin")
	public ResponseEntity<List<Teachers>> getAllPendingTeachers() {

		System.out.println("<=====>AdminController  getAllPendingTeachers()=====>");
		List<Teachers> teachers = teacherService.getTeachersByStatus("Pending");
		if (teachers == null)
			return ResponseEntity.status(HttpStatus.OK).body(teachers);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teachers);
	}

	

	// Through this API we get all Teachers whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/accepted-teacher")
	@Secured("admin")
	public ResponseEntity<List<Teachers>> getAllAcceptedTeachers() {

		System.out.println("<=====>AdminController  getAllAcceptedTeachers()=====>");
		List<Teachers> teachers = teacherService.getTeachersByStatus("Accepted");
		if (teachers == null)
			return ResponseEntity.status(HttpStatus.OK).body(teachers);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teachers);
	}


	// This method is used to accept the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@PostMapping("/approve-request/{email}")
	public ResponseEntity<?> approveRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController   approveRequest()=====>"+email);

		// Setting email information
		// Email email = new Email();
		// email.setCc(shopkeeperRepository.getOwnerEmailByShopEmail(shopEmail));
		// email.setTo(shopEmail);
		// email.setSubject("Shop has been approved.");
		// email.setMessage(
		// 		"Greetings for the day! \r\nThis mail is being sent to you by SalonSphere regarding your request for adding a shop.\r\n Congratulations your shop has been approved by our team. We wish you the best for you upcoming Journey.\r\n\r\n Thank you for connecting with us\r\nSalonSphere Inc. ");

		// // calling Email Service
		// EmailService.sendEmail(email);
		teacherService.updateStatus(email, "Accepted");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// This method is used to reject the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	 @Secured("admin")
	@PostMapping("/reject-request/{email}")
	public ResponseEntity<?> rejectRequest(@PathVariable String email){

		System.out.println("<=====>AdminController  rejectRequest()=====>"+email);
		// // Setting email information
		// Email email = new Email();
		// email.setCc(shopkeeperRepository.getOwnerEmailByShopEmail(shopEmail));
		// email.setTo(shopEmail);
		// email.setSubject("Shop has been rejected.");
		// email.setMessage(
		// 		"Greetings for the day! \r\nThis mail is being sent to you by SalonSphere regarding your request for adding a shop.\r\n Sorry to Inform you that the request for adding your Shop could not be considered due to incompatible details.\r\nYou can try again after 15 days .\r\n Thank You for Connecting with Us\r\n We wish you the best!!!\r\nSalonSphere Inc. ");

		// // calling Email Service
		// EmailService.sendEmail(email);
		teacherService.updateStatus(email, "rejected");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
