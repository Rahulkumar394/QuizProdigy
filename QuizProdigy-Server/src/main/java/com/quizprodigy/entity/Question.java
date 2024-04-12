package com.quizprodigy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {

    @Id
    @Column(name = "question_id")
    private String questionId;

    @Column(name = "question")
    private String question;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnore // Exclude this field from JSON serialization (optional)
    private Exam exam;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Options> options;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answers> answers; // One question has one answer

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", question='" + question + '\'' +
                ", examId='" + (exam != null ? exam.getExamId() : null) + '\'' + // Print only exam ID
                ", optionsCount=" + (options != null ? options.size() : 0) +  // Print only option count to avoid infinite loop
                ", answersCount=" + (answers != null ? answers.size() : 0) +
                '}';
    }
}
