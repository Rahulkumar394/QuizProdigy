package com.quizprodigy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean setExamQuestionPaper(List<SetQuestionAnswerRequest> questions) {

        // If the input parameter is null, return false indicating failure
        if(questions == null || questions.isEmpty()) return false;

        // Now we will Validate question list from here
        // Creating QuestionList object
        List<Question> questionsList = new ArrayList<>();
        // Creating OptionsList object
        List<Options> optionsList = new ArrayList<>();
        // Creation answerList object
        List<Answers> answersList = new ArrayList<>();
        // this flag  is used to check exam is set or not in database 
        boolean isSetExam = true;     
        // Make ExamId
        long totalNoOfExamIdCount = examRepository.count() + 1; // Get total count of exams in the repository
        String  examId="EXAM-"+totalNoOfExamIdCount;// Set the Exam ID EXAM-1

        for (SetQuestionAnswerRequest q : questions) {
            Exam exam = q.getExam();
            exam.setExamId(examId);
            // Validate Exam object
            if (isSetExam) {
                if (exam.getSubjectName().length() >= 2 && exam.getTotalQuestions() >= 1
                        && exam.getTotalTime() >= 1) {

                    java.util.Date utilDate = new java.util.Date(); // Create a java.util.Date object
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convert java.util.Date to
                                                                                   // java.sql.Date
                    exam.setCreatedDate(sqlDate); // Set created date
                    exam.setModifyDate(sqlDate); // Set modify date
                    exam.setDelete(false); // Set delete flag to false
                    examRepository.save(exam); // saving exam in database
                    isSetExam = false;
                }else
                return false;// Exam validation failed
            }

            // Creating Question object
            Question question = new Question();
            // Creating OptionsLisy object
            List<Options> options = new ArrayList<>();
            // Creation answer object
            Answers answers = new Answers();

            // Setting values in Question Object
            question.setQuestionId(UUID.randomUUID().toString()); // Generate UUID for question ID
            question.setQuestion(q.getQuestion()); // Set question text
            question.setExam(exam); // Associate question with exam

            // setting values in OptionsList Object
            Map<Character, String> optionMap = q.getOptions();
            for (Map.Entry<Character, String> entry : optionMap.entrySet()) {
                Character key = entry.getKey();
                String value = entry.getValue();

                Options option = new Options();
                option.setOptionId(UUID.randomUUID().toString()); // Generate UUID for option ID
                option.setOptionName(key); // Set option name (A, B, C, etc.)
                option.setOptionValue(value); // Set option value (text)
                option.setQuestion(question); // Associate option with question
                // set single option in option List
                options.add(option);
            }

            // setting values in Answer Object
            answers.setAnswerId(UUID.randomUUID().toString()); // Generate UUID for answer ID
            char[] ans = q.getAnswer().toCharArray();// Convert the answer String to a char array
            Arrays.sort(ans);// Sort the characters in the answer array in ascending order
            // Convert the sorted char array back to a String and set it as the correct
            // option for the answer
            answers.setCorrectOption(ans.toString());
            answers.setQuestion(question); // Associate answer with question

            // set optionList in question object
            question.setOptions(options);
            for (Options opt : options) {
                optionsList.add(opt); // Add options to optionsList for saving later
            }
            // set question in questionList object
            questionsList.add(question); // Add question to questionsList for saving later
            // set answer in answerList object
            answersList.add(answers); // Add answer to answersList for saving later
        }
        // saving all the questions in database
        questionRepository.saveAll(questionsList);
        // saving all Optionlist in database
        optionsRepository.saveAll(optionsList);
        // saving all Answer in database
        answersRepository.saveAll(answersList);
        return true; // successful creation of Exam and Questions
    }

    // This method retrieves an existing Exam from the Database by its ExamId and
    // constructs an ExamQuestionAnswerResponse object containing exam details,
    // questions, options, and answers.
    public ExamQuestionAnswerResponse getExamDetailsByID(String examId) {
        // Initialize the response object
        ExamQuestionAnswerResponse response = new ExamQuestionAnswerResponse();

        // Retrieve the existing Exam from the repository by its ExamId
        @SuppressWarnings("null")
        Optional<Exam> existingExam = examRepository.findById(examId);
        if (existingExam.isPresent()) {
            // If the Exam exists, extract its details
            Exam exam = existingExam.get();
            response.setExam(exam); // Set the Exam in the response object

            // Retrieve the list of questions associated with the Exam
            List<Question> questions = questionRepository.findByExam(exam);
            response.setQuestions(questions); // Set the list of questions in the response object

            // Initialize lists to store options and answers
            List<Options> options = new ArrayList<>();
            List<Answers> answers = new ArrayList<>();

            // Iterate through each question to retrieve its options and answer
            for (Question question : questions) {
                // Retrieve the options for the current question
                List<Options> questionOptions = optionsRepository.findByQuestion(question);
                options.addAll(questionOptions); // Add options to the options list

                // Retrieve the answer for the current question
                Answers answer = answersRepository.findByQuestion(question);
                answers.add(answer); // Add answer to the answers list
            }

            // Set the options and answers lists in the response object
            response.setOptions(options);
            response.setAnswers(answers);
        }

        // Return the constructed response object
        return response;
    }

    // This method updates the Exam, Questions, Options, and Answers if they exist
    // in the database.
    public boolean updateExamQuestionAnswer(ExamQuestionAnswerResponse updateExam) {
        if (updateExam == null) {
            // If the input parameter is null, return false indicating failure
            return false;
        }

        Exam exam = updateExam.getExam();
        List<Question> questions = updateExam.getQuestions();
        List<Options> options = updateExam.getOptions();
        List<Answers> answers = updateExam.getAnswers();

        if (exam == null || questions == null || options == null || answers == null) {
            // If any essential data is missing, return false indicating failure
            return false;
        }

        // Check if the exam already exists in the database
        @SuppressWarnings("null")
        Optional<Exam> existingExamOptional = examRepository.findById(exam.getExamId());
        if (existingExamOptional.isPresent()) {
            Exam existingExam = existingExamOptional.get();

            // Update Exam details
            existingExam.setSubjectName(exam.getSubjectName());
            existingExam.setTotalQuestions(exam.getTotalQuestions());
            existingExam.setTotalTime(exam.getTotalTime());
            existingExam.setPerQuestionTime(exam.getPerQuestionTime());
            existingExam.setModifyDate(new java.sql.Date(new java.util.Date().getTime()));

            // Update Questions, Options, and Answers
            for (Question question : questions) {
                // Check if the question already exists in the database
                @SuppressWarnings("null")
                Optional<Question> existingQuestionOptional = questionRepository.findById(question.getQuestionId());
                if (existingQuestionOptional.isPresent()) {
                    Question existingQuestion = existingQuestionOptional.get();

                    // Update Question details
                    existingQuestion.setQuestion(question.getQuestion());

                    // Update Options for the existing question
                    List<Options> existingOptions = optionsRepository.findByQuestion(existingQuestion);
                    for (Options option : options) {
                        // Check if the option already exists for the question
                        if (existingOptions.stream().anyMatch(opt -> opt.getOptionId().equals(option.getOptionId()))) {
                            // Update Option details
                            for (Options existingOption : existingOptions) {
                                if (existingOption.getOptionId().equals(option.getOptionId())) {
                                    // Update option value and name
                                    existingOption.setOptionValue(option.getOptionValue()); // Update option value
                                    existingOption.setOptionName(option.getOptionName()); // Update option name
                                    // Save the updated Option
                                    optionsRepository.save(existingOption);
                                    // Break the loop as we have updated the existing option
                                    break;
                                }
                            }

                        } else {
                            // Add new Option for the existing question
                            option.setQuestion(existingQuestion);
                            // Save the new Option
                            optionsRepository.save(option);
                        }
                    }

                    // Update Answers for the existing question
                    Answers existingAnswer = answersRepository.findByQuestion(existingQuestion);
                    if (existingAnswer != null) {
                        // Update Answer details
                        existingAnswer.setCorrectOption(answers.get(0).getCorrectOption()); // Assuming there is only
                                                                                            // one answer for a question
                        char[] ans = existingAnswer.getCorrectOption().toCharArray();// Convert the answer String to a
                                                                                     // char array
                        Arrays.sort(ans);// Sort the characters in the answer array in ascending order
                        existingAnswer.setCorrectOption(ans.toString());
                        // Save the updated Answer
                        answersRepository.save(existingAnswer);
                    }
                } else {
                    // Add new Question
                    question.setExam(existingExam);
                    // Save the new Question
                    questionRepository.save(question);
                }
            }
            // Save/update Exam in the database
            examRepository.save(existingExam);

            // Return true indicating successful update
            return true;
        }
        // Return false if the exam does not exist
        return false;
    }

    public boolean deleteExamById(String examId) {
        return examRepository.deleteByExamId(examId);
    }

}
