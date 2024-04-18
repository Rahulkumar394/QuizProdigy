package com.quizprodigy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizprodigy.entity.Students;
import com.quizprodigy.request.SetQuestionAnswerRequest;
import com.quizprodigy.response.GetQuestionAnswerResponse;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.ExamService;
import com.quizprodigy.service.StudentService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private ExamService examService;
	@Autowired
	private StudentService studentService;

	// Through this API we can setExamAndQuestionPaper
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/set-exam-question")
	public ResponseEntity<Response> setExamQuestion(@RequestBody SetQuestionAnswerRequest questions) {

		System.out.println("<=====>TeacherController  setExamQuestion()=====>\n" + questions);
		boolean isSuccessful = examService.setExamQuestionPaper(questions);
		if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Exam added Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Error while adding exam"));
	}

	// Through this API we can setExamAndQuestionPaper
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-allexamid-by-teacherid/{teacherId}")
	public ResponseEntity<List<String>> getAllExamIdByTeacherId(@PathVariable String teacherId) {

		System.out.println("<=====>TeacherController  getAllExamIdByTeacherId()=====>\n" + teacherId);
		List<String> examsIds = examService.getAllExamIdByTeacherId(teacherId);
		System.out.println("====ResponseEntity Examid==" + examsIds);
		if (!examsIds.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(examsIds);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(examsIds);
	}

	// Through this API we can find Exam Details alon with Questions and Answers by
	// providing the examId
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/exam-question/{examId}")
	@Secured("Teacher")
	public ResponseEntity<GetQuestionAnswerResponse> getExamDetailsByExamId(@PathVariable String examId) {

		System.out.println("<=====>TeacherController getExamDetailsByExamId()=====>\n" + examId);
		GetQuestionAnswerResponse examDetails = examService.getExamDetailsById(examId);
		return ResponseEntity.status(HttpStatus.OK).body(examDetails);
	}

	// Through this API we can update QuestionAnswer
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/update-exam-question")
	@Secured("Teacher")
	public ResponseEntity<Response> updateExamQuestion(@RequestBody GetQuestionAnswerResponse updateQuestion) {

		System.out.println("<=====>TeacherController updateExamQuestion()=====>\n" + updateQuestion);
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
	@GetMapping("/delete-exam/{examId}")
	@Secured("Teacher")
	public ResponseEntity<Response> deleteExam(@PathVariable String examId) {

		System.out.println("<=====>TeacherController  deleteExam() <=====>\n" + examId);
		boolean isSuccessful = examService.deleteExamById(examId);
		if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response("Error while deleting exam"));
	}

	// Through this API we get all Students whose status is "Pending" (account is
	// not activated yet)
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pending-student")
	@Secured("Teacher")
	public ResponseEntity<List<Students>> getAllPendingStudents() {

		System.out.println("<=====>AdminController  getAllPendingStudents()=====>");
		List<Students> students = studentService.getStudentsByStatus("Pending");
		if (students != null)
			return ResponseEntity.status(HttpStatus.OK).body(students);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
	}

	// Through this API we get all Students whose status is "Accepted"
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/accepted-student")
	@Secured("Teacher")
	public ResponseEntity<List<Students>> getAllAcceptedStudents() {

		System.out.println("<=====>AdminController  getAllAcceptedStudents()=====>");
		List<Students> students = studentService.getStudentsByStatus("Accepted");
		if (students != null)
			return ResponseEntity.status(HttpStatus.OK).body(students);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
	}

	// This method is used to accept the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("Teacher")
	@GetMapping("/approve-request/{email}")
	public ResponseEntity<?> approveRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController   approveRequest()=====>" + email);
		studentService.updateStatus(email, "Accepted");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// This method is used to reject the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("Teacher")
	@GetMapping("/reject-request/{email}")
	public ResponseEntity<?> rejectRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController  rejectRequest()=====>" + email);
		studentService.updateStatus(email, "rejected");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
