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
public class Options {

    @Id
	@Column(name = "option_id")// uuid
	private String optionId;

    @Column(name = "option_value",nullable = false)
    private String optionValue;

    // @Column(name = "option_name",nullable = false)
    // private char optionName;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
