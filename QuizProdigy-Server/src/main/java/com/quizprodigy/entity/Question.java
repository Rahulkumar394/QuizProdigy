package com.quizprodigy.entity;

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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Question {

    @Id
    @Column(name = "question_id")
    private String questionId;

    @Column(name = "exam_id")
    private int examId;

    @Column(name = "question")
    private String question;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}
