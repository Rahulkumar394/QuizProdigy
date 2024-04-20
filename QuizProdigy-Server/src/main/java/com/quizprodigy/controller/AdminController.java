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

import com.quizprodigy.dto.ExamScheduleDTO;
import com.quizprodigy.dto.ExamTimeTableDTO;
import com.quizprodigy.entity.Students;
import com.quizprodigy.entity.Teachers;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.ExamService;
import com.quizprodigy.service.StudentService;
import com.quizprodigy.service.TeacherService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ExamService examService;

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
		return ResponseEntity.status(HttpStatus.OK).body(students);

	}

	// This method is used to accept the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@GetMapping("/approve-request/{email}")
	public ResponseEntity<Response> approveRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController approveRequest()=====>" + email);
		teacherService.updateStatus(email, "Accepted");
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Success"));
	}

	// This method is used to reject the Teacher Request by admin
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@GetMapping("/reject-request/{email}")
	public ResponseEntity<Response> rejectRequest(@PathVariable String email) {

		System.out.println("<=====>AdminController  rejectRequest()=====>" + email);
		teacherService.updateStatus(email, "Rejected");
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Success"));
	}

	// Set exam time table through examid
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@PostMapping("/set-exam-timetable")
	public ResponseEntity<Response> setExamtimeTable(@RequestBody ExamTimeTableDTO examTimeTableDTO) {

		System.out.println("<=====>AdminController  setExamtimeTable()=====>" + examTimeTableDTO);
		boolean isSet = examService.setExamTimeTable(examTimeTableDTO);
		if (isSet) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Success"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Failure"));
	}

	// Set exam isstart flag STARTES that means exam is start now by examid
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@GetMapping("/start-exam/{examId}")
	public ResponseEntity<Response> startExam(@PathVariable String examId) {

		System.out.println("<=====>AdminController  startExam()=====>" + examId);
		boolean isSet = examService.startExam(examId);
		if (isSet) {
			ResponseEntity.status(HttpStatus.OK).body(new Response("Success"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Failure"));
	}

	// through this method we gat exam info which is not scheduale yet
	@CrossOrigin(origins = "http://localhost:4200")
	@Secured("admin")
	@GetMapping("examInfo")
	public ResponseEntity<List<ExamScheduleDTO>> getExamInfom() {

		System.out.println("<=====>AdminController  getExamInfom()=====>");
		List<ExamScheduleDTO> examScheduleDTO = examService.getExamInfo();
		System.out.println("Resonse=========>\n"+examScheduleDTO);
		if (examScheduleDTO!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(examScheduleDTO);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(examScheduleDTO);
	}

}
