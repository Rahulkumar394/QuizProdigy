package com.quizprodigy.request;

import java.util.List;

import com.quizprodigy.utility.IdsWitheValue;

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
public class SubmitExam {

    private String studentId;
    private String examId;
    private List<IdsWitheValue> answerList;
}
