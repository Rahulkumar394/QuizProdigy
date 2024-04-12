package com.quizprodigy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Answers {

    @Id
	@Column(name = "answer_id")// uuid
	private String answerId;

    @Column(name = "correct_answer",nullable = false)
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore // Exclude this field from JSON serialization
    private Question questionId;
    
    @Override
    public String toString() {
        return "Answers{" +
                "answerId='" + answerId + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' + // Assuming a property named answerText
                ", questionId='" + (questionId!=null ? questionId.getQuestionId():null)+'\''+
                '}';
    }
}
