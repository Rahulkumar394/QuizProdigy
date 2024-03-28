package com.quizprodigy.response;

import java.util.List;

import com.quizprodigy.entity.Answers;
import com.quizprodigy.entity.Exam;
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
public class ExamQuestionAnswerResponse {

    private Exam exam;
    private List<Question> questions;
    private List<Options> options;
    private List<Answers> answers;   

}
