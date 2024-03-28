package com.quizprodigy.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "subject_name",nullable = false)
    private String subjectName;

    @Column(name = "total_questions",nullable = false)
    private int totalQuestions;

    @Column(name = "total_time",nullable = false)
    private int totalTime;

    @Column(name = "per_question_time")
    private int perQuestionTime;

    @Column(name = "created_date",nullable = false)
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

    @OneToMany(mappedBy = "exam")
    private List<Question> questions;

}
