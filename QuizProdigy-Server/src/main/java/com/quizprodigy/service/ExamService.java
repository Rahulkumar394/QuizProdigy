package com.quizprodigy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.quizprodigy.dto.QuestionDto;
import com.quizprodigy.entity.Answers;
import com.quizprodigy.entity.Exam;
import com.quizprodigy.entity.Options;
import com.quizprodigy.entity.Question;
import com.quizprodigy.repository.AnswersRepository;
import com.quizprodigy.repository.ExamRepository;
import com.quizprodigy.repository.OptionsRepository;
import com.quizprodigy.repository.QuestionRepository;
import com.quizprodigy.request.SetQuestionAnswerRequest;
import com.quizprodigy.response.ExamQuestionAnswerResponse;

public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionsRepository optionsRepository;
    @Autowired
    private AnswersRepository answersRepository;

    // Through this method we can set Exam and Question Answer
    public boolean setExamQuestionPaper(SetQuestionAnswerRequest questions) {

        // If the input parameter is null, return false indicating failure
        if (questions == null)
            return false;

        // Now we will Validate question list from here
        // Creating QuestionList object
        List<Question> questionsList = new ArrayList<>();
        // Creating OptionsList object
        List<Options> optionsList = new ArrayList<>();
        // Creation answerList object
        List<Answers> answersList = new ArrayList<>();

        // Extract Exam and Question Details From Request Object
        Exam exam = questions.getExam();
        List<QuestionDto> questionDtos = questions.getQuestions();

        // Make ExamId
        long totalNoOfExamIdCount = examRepository.count() + 1; // Get total count of exams in the repository
        exam.setExamId("EXAM-" + totalNoOfExamIdCount);// Set the Exam ID EXAM-1
        if (exam.getSubjectName().length() >= 2 && exam.getTotalQuestions() >= 1 && exam.getTotalTime() >= 1) {

            java.util.Date utilDate = new java.util.Date(); // Create a java.util.Date object
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convert java.util.Date to java.sql.Date
            exam.setCreatedDate(sqlDate); // Set created date
            exam.setModifyDate(sqlDate); // Set modify date
            exam.setDelete(false); // Set delete flag to false
            exam = examRepository.save(exam); // saving exam in database

            if (exam != null) {
                for (QuestionDto q : questionDtos) {
                    // Setting question fields
                    Question question = q.getQuestion();
                    question.setExam(exam); // Setting up foreign key reference
                    question.setQuestionId(UUID.randomUUID().toString()); // Generating random UUID as Question Id

                    // Setting option
                    List<Options> oList = q.getOptions();
                    for (Options option : oList) {
                        Options opt = new Options();
                        opt.setOptionId(UUID.randomUUID().toString());
                        opt.setOptionValue(option.getOptionValue());
                        opt.setQuestion(question); // Setting up Foreign Key Reference
                        optionsList.add(opt);
                    }

                    // setting answer
                    List<Answers> ansList = q.getAnswer();
                    for (Answers ans : ansList) {
                        Answers answer = new Answers();
                        answer.setAnswerId(UUID.randomUUID().toString());
                        answer.setCorrectAnswer(ans.getCorrectAnswer());
                        answer.setQuestion(question); // Setting up Foreign Key Reference
                        ansList.add(answer);
                    }

                    // Setting question Remaning Fields
                    question.setOptions(optionsList); // Adding all Option list to question Object
                    question.setAnswer(ansList); // Adding Answer list to question Object
                    questionsList.add(question); // Add question to the list of questions

                }
                // saving all the questions in database
                questionRepository.saveAll(questionsList);
                // saving all Optionlist in database
                optionsRepository.saveAll(optionsList);
                // saving all Answer in database
                answersRepository.saveAll(answersList);
                return true; // successful creation of Exam and Questions
            }
        }
        return false; // unsuccessful creation of Exam and Question
    }

    // This method retrieves an existing Exam from the Database by its ExamId and
    // constructs an ExamQuestionAnswerResponse object containing exam details,
    // questions, options, and answers.
    public SetQuestionAnswerRequest getExamDetailsById(String examId) {
        // Initialize the response object
        SetQuestionAnswerRequest response = new SetQuestionAnswerRequest();

        // Retrieve the existing Exam from the repository by its ExamId
        @SuppressWarnings("null")
        Optional<Exam> existingExam = examRepository.findById(examId);
        if (existingExam.isPresent()) {
            // If the Exam exists, extract its details
            Exam exam = existingExam.get();
            response.setExam(exam); // Set the Exam in the response object

            List<QuestionDto> questionDtos = new ArrayList<>();
            // Retrieve the list of questions associated with the Exam
            List<Question> questions = questionRepository.findByExam(exam);

            for (Question question : questions) {
                // Create a DTO containing only the necessary fields for displaying on the
                // front-end
                QuestionDto questionDto = new QuestionDto();
                questionDto.setQuestion(question);

                // Retrieve the options for the current question
                List<Options> questionOptions = optionsRepository.findByQuestion(question);
                questionDto.setOptions(questionOptions);

                // Retrieve the answers for the current question
                List<Answers> answer = answersRepository.findByQuestion(question);
                questionDto.setAnswer(answer);

                questionDtos.add(questionDto);
            }
            response.setQuestions(questionDtos); // Add the list of questions to the response object
            return response;// Return the constructed response object
        }
        return null;
    }

    // This method updates the Exam, Questions, Options, and Answers if they exist
    // in the database.
    @SuppressWarnings("null")
    public boolean updateExamQuestionAnswer(SetQuestionAnswerRequest updateExam) {
        // If the input parameter is null, return false indicating failure
        if (updateExam == null && updateExam.getExam() == null && updateExam.getQuestions() == null)
            return false;

        // Extract Exam and Question Details From Request Object
        Exam exam = updateExam.getExam();
        List<QuestionDto> updateQuestionDtos = updateExam.getQuestions();

        // Check if the exam already exists in the database
        Optional<Exam> existingExamOptional = examRepository.findById(exam.getExamId());

        if (existingExamOptional.isPresent()) {
            Exam existingExam = existingExamOptional.get();

            // Update Exam details
            existingExam.setSubjectName(exam.getSubjectName());
            existingExam.setTotalQuestions(exam.getTotalQuestions());
            existingExam.setTotalTime(exam.getTotalTime());
            existingExam.setPerQuestionTime(exam.getPerQuestionTime());
            existingExam.setModifyDate(new java.sql.Date(new java.util.Date().getTime()));

            for (QuestionDto questionDto : updateQuestionDtos) {

                // List<Question> questions = new ArrayList<>();
                List<Options> options = new ArrayList<>();
                List<Answers> answers = new ArrayList<>();

                Optional<Question> questionFromDBOptional = questionRepository
                        .findById(questionDto.getQuestion().getQuestionId());

                // Question is present in DB
                if (questionFromDBOptional.isPresent()) {
                    Question existingQuestion = questionFromDBOptional.get();
                    existingQuestion.setQuestion(questionDto.getQuestion().getQuestion());
                    existingQuestion.setOptions(questionDto.getOptions());
                    existingQuestion.setAnswer(questionDto.getAnswer());

                    questionRepository.save(existingQuestion);
                    optionsRepository.saveAll(questionDto.getOptions());
                    answersRepository.saveAll(questionDto.getAnswer());
                }
            }
            examRepository.save(exam);
            return true;
        }
        return false;
    }

    // Through this method, we can delete an exam from the database
    public boolean deleteExamById(String examId) {
        return examRepository.deleteByExamId(examId);
    }

}
