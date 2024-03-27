package com.quizprodigy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Answers {

    @Id
	@Column(name = "answer_id")// uuid
	private String answerId;

    @Column(name = "question_id",nullable = false)
	private String questionId;

    @Column(name = "correct_option",nullable = false)
    private String correctOption;

}
