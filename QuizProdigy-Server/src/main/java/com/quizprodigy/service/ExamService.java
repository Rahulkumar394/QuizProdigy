package com.quizprodigy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizprodigy.dto.ExamQuestionDto;
import com.quizprodigy.dto.ExamScheduleDTO;
import com.quizprodigy.dto.ExamTimeTableDTO;
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
import com.quizprodigy.response.GetQuestionAnswerResponse;
import com.quizprodigy.utility.IdsWitheValue;

@Service
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

		System.out.println("Inside  setExamQuestionPaper exam service");
		// If the input parameter is null, return false indicating failure
		if (questions == null)
			return false;

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
			exam.setExamStatus("NOT STARTED");
			exam.setStart(false);
			exam = examRepository.save(exam); // saving exam in database

			if (exam != null) {
				for (QuestionDto q : questionDtos) {
					Question question = new Question();// Create question object
					question.setQuestion((q.getQuestion()));// set actual question inside question object
					question.setExam(exam); // Setting up foreign key reference
					question.setQuestionId(UUID.randomUUID().toString()); // Generating random UUID as Question Id
					System.out.println(question.getQuestionId() + "Hello Bha9i Question ID Wale6548647532153876854132");
					// Setting option
					List<String> oList = q.getOptions();
					for (String option : oList) {
						Options opt = new Options();
						opt.setOptionId(UUID.randomUUID().toString());
						opt.setOptionValue(option);
						opt.setQuestionId(question); // Setting up Foreign Key Reference
						optionsList.add(opt);
					}

					// setting answer
					List<String> ansList = q.getAnswer();
					for (String ans : ansList) {
						Answers answer = new Answers();
						answer.setAnswerId(UUID.randomUUID().toString());
						answer.setCorrectAnswer(ans);
						answer.setQuestionId(question); // Setting up Foreign Key Reference
						answersList.add(answer);
					}

					// Setting question Remaning Fields
					question.setOptions(optionsList); // Adding all Option list to question Object
					question.setAnswers(answersList); // Adding Answer list to question Object
					questionRepository.save(question);// Saving Question along with options and answers in DB

				}
				return true; // successful creation of Exam and Questions
			}
		}
		return false; // unsuccessful creation of Exam and Question
	}

	// Through this method we can find all examId by teacherId
	public List<String> getAllExamIdByTeacherId(String teacherId) {
		return examRepository.findExamIdByTeacherId(teacherId);
	}

	// This method retrieves an existing Exam from the Database by its ExamId and
	// constructs an ExamQuestionAnswerResponse object containing exam details,
	// questions, options, and answers.
	// public SetQuestionAnswerRequest getExamDetailsById(String examId) {
	// 	System.out.println("<==Inside  getExamDetailsById exam service==>");
	// 	// Initialize the response object
	// 	SetQuestionAnswerRequest response = new SetQuestionAnswerRequest();

	// 	// Retrieve the existing Exam from the repository by its ExamId
	// 	Exam exam = examRepository.findByExamId(examId);
	// 	if (exam != null) {
	// 		// If the Exam exists, extract its details

	// 		response.setExam(exam); // Set the Exam in the response object

	// 		List<QuestionDto> questionDtos = new ArrayList<>();
	// 		// Retrieve the list of questions associated with the Exam
	// 		List<Question> questions = questionRepository.findByExam(exam);

	// 		for (Question question : questions) {
	// 			// Create a DTO containing only the necessary fields for displaying on the
	// 			// front-end
	// 			QuestionDto questionDto = new QuestionDto();
	// 			questionDto.setQuestion(question.getQuestion());

	// 			// Retrieve the options for the current question
	// 			List<Options> questionOptions = optionsRepository.findByQuestionId(question);
	// 			List<String> optionPerQuestion = new ArrayList<>();
	// 			for (Options option : questionOptions) {
	// 				optionPerQuestion.add(option.getOptionValue());
	// 			}
	// 			questionDto.setOptions(optionPerQuestion);

	// 			// Retrieve the answers for the current question
	// 			List<Answers> answers = answersRepository.findByQuestionId(question);
	// 			List<String> answerPerQuestion = new ArrayList<>();
	// 			for (Answers ans : answers) {
	// 				answerPerQuestion.add(ans.getCorrectAnswer());
	// 			}
	// 			questionDto.setAnswer(answerPerQuestion);
	// 			questionDtos.add(questionDto);
	// 		}
	// 		response.setQuestions(questionDtos); // Add the list of questions to the response object
	// 		return response;// Return the constructed response object
	// 	}
	// 	return null;
	// }

	/////////////////////////////////////////////////////////////////////////////

	public GetQuestionAnswerResponse getExamDetailsById(String examId) {
		System.out.println("<==Inside  getExamDetailsById exam service==>");
		// Initialize the response object

		GetQuestionAnswerResponse questionAnswerResponse = new GetQuestionAnswerResponse();

		// Retrieve the existing Exam from the repository by its ExamId
		Exam exam = examRepository.findByExamId(examId);
		if (exam != null) {
			// If the Exam exists, extract its details

			questionAnswerResponse.setExam(exam); // Set the Exam in the response object

			List<ExamQuestionDto> examQuestionDtos = new ArrayList<>();
			// Retrieve the list of questions associated with the Exam
			List<Question> questions = questionRepository.findByExam(exam);

			for (Question question : questions) {
				// Create a DTO containing only the necessary fields for displaying on the
				// front-end
				ExamQuestionDto examQuestionDto = new ExamQuestionDto();

				// Set the question along with id in IdsWithValue class object
				IdsWitheValue qIdsWitheValue = new IdsWitheValue();
				qIdsWitheValue.setId(question.getQuestionId());
				qIdsWitheValue.setValue(question.getQuestion());
				examQuestionDto.setQuestion(qIdsWitheValue);

				// Retrieve the options for the current question
				List<Options> questionOptions = optionsRepository.findByQuestionId(question);
				List<IdsWitheValue> opIdsWitheValues = new ArrayList<>();
				for (Options option : questionOptions) {
					IdsWitheValue obj = new IdsWitheValue();
					obj.setId(option.getOptionId());
					obj.setValue(option.getOptionValue());
					opIdsWitheValues.add(obj);
				}
				examQuestionDto.setOptions(opIdsWitheValues);

				// Retrieve the answers for the current question
				List<Answers> answers = answersRepository.findByQuestionId(question);
				List<IdsWitheValue> ansIdsWitheValues = new ArrayList<>();
				for (Answers ans : answers) {
					IdsWitheValue obj = new IdsWitheValue();
					obj.setId(ans.getAnswerId());
					obj.setValue(ans.getCorrectAnswer());
					ansIdsWitheValues.add(obj);
				}
				examQuestionDto.setAnswers(ansIdsWitheValues);
				examQuestionDtos.add(examQuestionDto);
			}
			questionAnswerResponse.setQuestions(examQuestionDtos);// Add the list of questions to the response object
			return questionAnswerResponse;// Return the constructed response object
		}
		return null;
	}

	@SuppressWarnings("null")
	public boolean updateExamQuestionAnswer(GetQuestionAnswerResponse updateExam) {
		// If the input parameter is null, return false indicating failure
		if (updateExam == null && updateExam.getExam() == null && updateExam.getQuestions() == null)
			return false;

		// Extract Exam and Question Details From Request Object
		Exam exam = updateExam.getExam();
		List<ExamQuestionDto> updateExamQuestionDtos = updateExam.getQuestions();

		// Check if the exam already exists in the database
		Optional<Exam> existingExamOptional = examRepository.findById(exam.getExamId());
		if (existingExamOptional.isPresent()) {
			Exam existingExam = existingExamOptional.get();
			// Update Exam details
			existingExam.setSubjectName(exam.getSubjectName());
			existingExam.setTotalTime(exam.getTotalTime());
			existingExam.setModifyDate(new java.sql.Date(new java.util.Date().getTime()));

			for (ExamQuestionDto eQuestionDto : updateExamQuestionDtos) {
				Optional<Question> questionFromDBOptional = questionRepository
						.findById(eQuestionDto.getQuestion().getId());

				// Question is present in DB
				if (questionFromDBOptional.isPresent()) {
					Question existingQuestion = questionFromDBOptional.get();
					existingQuestion.setQuestion(eQuestionDto.getQuestion().getValue());
					// Update Question Options
					for (IdsWitheValue idsWitheValue : eQuestionDto.getOptions()) {
						Optional<Options> optinalOption = optionsRepository.findById(idsWitheValue.getId());
						if (optinalOption.isPresent()) {
							Options option = optinalOption.get();
							option.setOptionValue(idsWitheValue.getValue());
							optionsRepository.save(option);
						}

					}
					// existingQuestion.setOptions(options);
					// Update Question Answers
					for (IdsWitheValue idsWitheValue : eQuestionDto.getAnswers()) {
						Optional<Answers> optionalAnswer = answersRepository.findById(idsWitheValue.getId());
						if (optionalAnswer.isPresent()) {
							Answers answer = optionalAnswer.get();
							answer.setCorrectAnswer(idsWitheValue.getValue());
							answersRepository.save(answer);

						}
					}

					// Set the updated answers collection to the Question
					// existingQuestion.setAnswers(updatedAnswers);
					questionRepository.save(existingQuestion);
				}
			}
			examRepository.save(existingExam);
			return true;
		}
		return false;
	}

	// Through this method, we can delete an exam from the database
	@Transactional
	public boolean deleteExamById(String examId) {
		int i = examRepository.deleteByExamId(examId);
		return i == 1;
	}

	// through this method we can set the exam time table
	public boolean setExamTimeTable(ExamTimeTableDTO eQuestionDto) {
		String examId = eQuestionDto.getExamId();
		String date = eQuestionDto.getExamDate();// DD/MM/YYYY
		String time = eQuestionDto.getExamTime();// HH:MM
		Date examDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			examDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int i = examRepository.updateExamDateByExamId(examId, examDate,time);
		return i == 1;
	}

	public boolean startExam(String examId) {
		int i = examRepository.updateExamStatusByExamId(examId);
		return i == 1;
	}

	// through this method we gat exam info which is not scheduale yet
	public List<ExamScheduleDTO> getExamInfo() {
		return examRepository.findNotStartedExams();
	}

	// get exam information
	public Exam getExamInfo(String examId) {
		return examRepository.findByExamId(examId);
	}

}
