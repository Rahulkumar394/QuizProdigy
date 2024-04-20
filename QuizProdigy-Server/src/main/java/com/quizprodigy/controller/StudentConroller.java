package com.quizprodigy.controller;

import java.util.Map;

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

import com.quizprodigy.entity.Exam;
import com.quizprodigy.request.SubmitExam;
import com.quizprodigy.response.GetQuestionAnswerResponse;
import com.quizprodigy.response.Response;
import com.quizprodigy.service.ExamService;
import com.quizprodigy.service.PerformanceService;
import com.quizprodigy.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentConroller {

    @Autowired
    private ExamService examService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PerformanceService performanceService;

    // Through this API we can setExamAndQuestionPaper
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getexam/{examId}")
    @Secured("Student")
    public ResponseEntity<Exam> getExamById(@PathVariable String examId) {

        System.out.println("<=====>Student Controller  getExamById()=====>\n" + examId);
        Exam exam = examService.getExamInfo(examId);
        if (exam != null)
            return ResponseEntity.status(HttpStatus.OK).body(exam);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exam);
    }

    // Through this API we can setExamAndQuestionPaper
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/take-exam/{examId}")
    @Secured("Student")
    public ResponseEntity<GetQuestionAnswerResponse> takeExamById(@PathVariable String examId) {

        System.out.println("<=====>Student Controller  takeExamById()=====>\n" + examId);
        GetQuestionAnswerResponse question = studentService.takeExamById(examId);
        if (question != null)
            return ResponseEntity.status(HttpStatus.OK).body(question);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(question);
    }

    // Through this API we can setExamAndQuestionPaper
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/submitExam")
    @Secured("Student")
    public ResponseEntity<Response> submitExam(@RequestBody SubmitExam answer) {
        // Process the answerMap data here (e.g., save to a database)
        System.out.println("Received answerMap:==========================\n " + answer);
        boolean isSave = performanceService.savePerformance(answer);
        if (isSave)
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Success"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Faliure"));
    }

}
