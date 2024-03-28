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

import com.quizprodigy.entity.Exam;
import com.quizprodigy.request.SetQuestionAnswerRequest;
import com.quizprodigy.response.ExamQuestionAnswerResponse;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.TeacherService;

public class TeacherController {

	@Autowired
	private TeacherService teacherService;

    // Through this API we can setExamAndQuestionPaper 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/set-exam-question")
	@Secured("teacher")
	public ResponseEntity<Response> setExamQuestion(@RequestBody Exam exam, List<SetQuestionAnswerRequest> questions) {

		System.out.println("<=====>TeacherController  setExamQuestion()=====>\n"+exam+"\n"+questions);
		boolean  isSuccessful = teacherService.setExamQuestionPaper(exam, questions);
       if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error while adding Shop"));
	}


	// Through this API we can find Exam Details alon with  Questions and Answers by providing the examId
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/exam/{examId}")
	@Secured("teacher")
	public ResponseEntity<ExamQuestionAnswerResponse> getExamDetailsByExamId(@PathVariable String examId) {

		System.out.println("<=====>TeacherController getExamDetailsByExamId()=====>\n"+examId);
		ExamQuestionAnswerResponse  examDetails = teacherService.getExamDetailsByID(examId);
       if (examDetails!=null)
			return ResponseEntity.status(HttpStatus.OK).body(examDetails);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(examDetails);
	}




	// Through this API we can update QuestionAnswer
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/set-exam-question")
	@Secured("shopkeeper")
	public ResponseEntity<Response> updateExamQuestion(@RequestBody Exam exam, List<SetQuestionAnswerRequest> questions) {

		System.out.println("<=====>TeacherController  setExamQuestion()=====>\n"+exam+"\n"+questions);
		boolean  isSuccessful = teacherService.setExamQuestionPaper(exam, questions);
       if (isSuccessful)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully"));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Error while adding Shop"));
	}

}
