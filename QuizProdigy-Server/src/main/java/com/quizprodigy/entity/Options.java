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
public class Options {

    @Id
    @Column(name = "option_id") // uuid
    private String optionId;

    @Column(name = "option_value", nullable = false)
    private String optionValue;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore // Exclude this field from JSON serialization
    private Question questionId;
    
    @Override
    public String toString() {
      return "Options{" +
              "optionId='" + optionId + '\'' +
              ", optionValue='" + optionValue + '\'' +
              ", questionId ='" + (questionId !=null? questionId.getQuestionId():null)+
              '}';
    }
}
