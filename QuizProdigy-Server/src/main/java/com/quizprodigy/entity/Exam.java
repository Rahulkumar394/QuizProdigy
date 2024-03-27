package com.quizprodigy.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private int examId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "total_questions")
    private int totalQuestions;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "per_question_time")
    private int perQuestionTime;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "is_hands_on")
    private boolean isHandsOn;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teachers teacherId;

    @ManyToOne
    @JoinColumn(name = "student_id") // This is the foreign key column in the Exam table
    private Students student;

}
