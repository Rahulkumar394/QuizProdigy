package com.quizprodigy.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Performance {

	@Id
	@Column(name = "performance_id") // uuid
	private String performanceId;

	@Column(name = "total_question", nullable = false)
	private int totalQuestion;

	@Column(name = "attempted_question", nullable = false)
	private int attemptedQuestion;

	@Column(name = "unattempted_question", nullable = false)
	private int unattemptedQuestion;

	@Column(name = "correct_answer", nullable = false)
	private int correctAnswer;

	@Column(name = "employee_no", unique = true)
	private String employeeNo;

	@Column(name = "percentage", nullable = false)
	private double percentage; // calculated as (correct answer/ total question)*100

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "isdeleted", nullable = false)
	private boolean isDeleted;

	 @ManyToOne
	  @JoinColumn(name = "student_id") // Match the column name in your database
	  private Students student;

	@ManyToOne
	@JoinColumn(name = "examId")
	private Exam examId;
	
	
	 @Override
	    public String toString() {
	        return "Performance{" +
	                "performanceId='" + performanceId + '\'' +
	                ", totalQuestion=" + totalQuestion +
	                ", attemptedQuestion=" + attemptedQuestion +
	                ", unattemptedQuestion=" + unattemptedQuestion +
	                ", correctAnswer=" + correctAnswer +
	                ", employeeNo='" + employeeNo + '\'' +
	                ", percentage=" + percentage +
	                ", createdDate=" + createdDate +
	                ", modifyDate=" + modifyDate +
	                ", isDeleted=" + isDeleted +
	                ", studentId='" + (student != null ? student.getStudentId() : null) + '\'' + // Avoid infinite loop, print only student ID
	                ", examId='" + (examId != null ? examId.getExamId() : null) + '\'' + // Avoid infinite loop, print only exam ID
	                '}';
	    }
}
