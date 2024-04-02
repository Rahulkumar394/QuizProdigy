package com.quizprodigy.dto;

import java.util.List;

import com.quizprodigy.entity.Answers;
import com.quizprodigy.entity.Options;
import com.quizprodigy.entity.Question;

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
public class QuestionDto {
     
    private Question question;
    private List<Options> options;
    private List<Answers> answer;

}
