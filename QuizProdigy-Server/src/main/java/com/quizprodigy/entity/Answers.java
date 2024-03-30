package com.quizprodigy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class Answers {

    @Id
	@Column(name = "answer_id")// uuid
	private String answerId;

    @Column(name = "correct_option",nullable = false)
    private String correctOption;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question; // Each answer belongs to a single question
}
