package com.quizprodigy.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizprodigy.entity.Answers;
import com.quizprodigy.entity.Exam;
import com.quizprodigy.entity.Performance;
import com.quizprodigy.entity.Question;
import com.quizprodigy.entity.Students;
import com.quizprodigy.repository.AnswersRepository;
import com.quizprodigy.repository.ExamRepository;
import com.quizprodigy.repository.PerformanceRepository;
import com.quizprodigy.repository.StudentRepository;
import com.quizprodigy.request.SubmitExam;
import com.quizprodigy.utility.IdsWitheValue;

@Service
public class PerformanceService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private AnswersRepository answersRepository;
    @Autowired
    private StudentRepository studentsRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    public boolean savePerformance(SubmitExam submitExam) {
        System.out.println("<===INSISDE PERFORMANCE SERVICE SAVEPERFOMANCE METHOD==>");

        Optional<Exam> optionalExam = examRepository.findById(submitExam.getExamId());
        Optional<Students> optionalStudent = studentsRepository.findById(submitExam.getStudentId());
        if (optionalExam.isPresent() && optionalStudent.isPresent()) {
            Exam exam = optionalExam.get();
            Students student = optionalStudent.get();
            Performance performance = new Performance();

            // set perforance object
            performance.setExam(exam);
            performance.setStudent(student);
            performance.setPerformanceId(UUID.randomUUID().toString());
            performance.setTotalQuestion(exam.getTotalQuestions());
            performance.setAttemptedQuestion(submitExam.getAnswerList().size());
            performance.setUnattemptedQuestion(exam.getTotalQuestions() - submitExam.getAnswerList().size());
            Date date = new Date();
            performance.setCreatedDate(date);
            performance.setModifyDate(date);

            // calculate score
            int score = 0;
            List<IdsWitheValue> answerList = submitExam.getAnswerList();
            for (IdsWitheValue ans : answerList) {
                Question q = new Question();
                q.setQuestionId(ans.getId());
                List<Answers> answer = answersRepository.findByQuestionId(q);
                for (Answers correctans : answer) {
                    if (correctans.getCorrectAnswer().equals(ans.getValue()))
                        score++;
                }
            }
            performance.setCorrectAnswer(score);
            float fscore=score;
            performance.setPercentage((fscore / exam.getTotalQuestions()) * 100);
            // save performance
          //  System.err.println("========MY PERFORMANCE IS ======\n" + performance);
            performanceRepository.save(performance);
            return true;
        }

        return false;
    }

}
