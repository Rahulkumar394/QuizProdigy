package com.quizprodigy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quizprodigy.entity.Students;
import com.quizprodigy.request.SetQuestionAnswerRequest;
import com.quizprodigy.response.ExamQuestionAnswerResponse;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.ExamService;
import com.quizprodigy.service.StudentService;

@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private ExamService examService;
	@Autowired
	private StudentService studentService;

	// Through this API we can setExamAndQuestionPaper
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/set-exam-question")
	@Secured("Teacher")
	public ResponseEntity<Response> setExamQuestion(@RequestBody List<SetQuestionAnswerRequest> questions) {

		System.out.println("<=====>TeacherController  setExamQuestion()=====>\n" + questions);
		boolean isSuccessful = examService.setExamQuestionPaper(questions);
		if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Exam added Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Error while adding exam"));
	}

	// Through this API we can find Exam Details alon with Questions and Answers by
	// providing the examId
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/exam/{examId}")
	@Secured("Teacher")
	public ResponseEntity<ExamQuestionAnswerResponse> getExamDetailsByExamId(@PathVariable String examId) {

		System.out.println("<=====>TeacherController getExamDetailsByExamId()=====>\n" + examId);
		ExamQuestionAnswerResponse examDetails = examService.getExamDetailsByID(examId);
		if (examDetails != null)
			return ResponseEntity.status(HttpStatus.OK).body(examDetails);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(examDetails);
	}

	// Through this API we can update QuestionAnswer
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/set-exam-question")
	@Secured("Teacher")
	public ResponseEntity<Response> updateExamQuestion(@RequestBody ExamQuestionAnswerResponse updateQuestion) {

		System.out.println("<=====>TeacherController  setExamQuestion()=====>\n" + updateQuestion);
		boolean isSuccessful = examService.updateExamQuestionAnswer(updateQuestion);
		if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Error while updating exam"));
	}

	// Through this API we can delete exam as well as question options and answer
	// which is related to that exam
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/delete-exam/{examId}")
	@Secured("Teacher")
	public ResponseEntity<Response> deleteExam(@PathVariable String examId) {

		System.out.println("<=====>TeacherController  deleteExam()<=====>\n" + examId);
		boolean isSuccessful = examService.deleteExamById(examId);
		if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Error while deleting exam"));
	}

	////////////////////////////////

	// Through this API we get all Students whose status is "Pending" (account is
	// not activated yet)
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pending-student")
	@Secured("Teacher")
	public ResponseEntity<List<Students>> getAllPendingStudents() {

		System.out.println("<=====>AdminController  getAllPendingStudents()=====>");
		List<Students> students = studentService.getStudentsByStatus("Pending");
		if (students == null)
			return ResponseEntity.status(HttpStatus.OK).body(students);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
	}

	// Through this API we get all Students whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/accepted-student")
	@Secured("Teacher")
	public ResponseEntity<List<Students>> getAllAcceptedStudents() {

		System.out.println("<=====>AdminController  getAllAcceptedStudents()=====>");
		List<Students> students = studentService.getStudentsByStatus("Accepted");
		if (students == null)
			return ResponseEntity.status(HttpStatus.OK).body(students);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
	}

	// This method is used to accept the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("Teacher")
	@PostMapping("/approve-request/{email}")
	public ResponseEntity<?> approveRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController   approveRequest()=====>" + email);

		// Setting email information
		// Email email = new Email();
		// email.setCc(shopkeeperRepository.getOwnerEmailByShopEmail(shopEmail));
		// email.setTo(shopEmail);
		// email.setSubject("Shop has been approved.");
		// email.setMessage(
		// "Greetings for the day! \r\nThis mail is being sent to you by SalonSphere
		// regarding your request for adding a shop.\r\n Congratulations your shop has
		// been approved by our team. We wish you the best for you upcoming
		// Journey.\r\n\r\n Thank you for connecting with us\r\nSalonSphere Inc. ");

		// // calling Email Service
		// EmailService.sendEmail(email);
		studentService.updateStatus(email, "Accepted");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// This method is used to reject the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("Teacher")
	@PostMapping("/reject-request/{email}")
	public ResponseEntity<?> rejectRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController  rejectRequest()=====>" + email);
		// // Setting email information
		// Email email = new Email();
		// email.setCc(shopkeeperRepository.getOwnerEmailByShopEmail(shopEmail));
		// email.setTo(shopEmail);
		// email.setSubject("Shop has been rejected.");
		// email.setMessage(
		// "Greetings for the day! \r\nThis mail is being sent to you by SalonSphere
		// regarding your request for adding a shop.\r\n Sorry to Inform you that the
		// request for adding your Shop could not be considered due to incompatible
		// details.\r\nYou can try again after 15 days .\r\n Thank You for Connecting
		// with Us\r\n We wish you the best!!!\r\nSalonSphere Inc. ");

		// // calling Email Service
		// EmailService.sendEmail(email);
		studentService.updateStatus(email, "rejected");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
