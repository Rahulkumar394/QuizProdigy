package com.quizprodigy.request;


import java.util.Map;

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

    private String question;
    // Character is like (A,B,C...) and string is the answer for
    private Map<Character, String> options;
    private String answer;
    private Exam exam;

}
