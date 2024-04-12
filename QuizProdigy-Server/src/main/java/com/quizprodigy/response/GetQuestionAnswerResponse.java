package com.quizprodigy.response;

import java.util.List;

import com.quizprodigy.dto.ExamQuestionDto;
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
public class GetQuestionAnswerResponse {

    private List<ExamQuestionDto> questions;
    private Exam exam;
}
