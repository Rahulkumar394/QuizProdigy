package com.quizprodigy.request;


import java.util.List;

import com.quizprodigy.dto.QuestionDto;
import com.quizprodigy.entity.Exam;

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
public class SetQuestionAnswerRequest {

    private List<QuestionDto> questions;
    private Exam exam;

}
