package com.quizprodigy.dto;

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
public class ExamQuestionDto {
    private IdsWitheValue question;
    private List<IdsWitheValue> options;
    private List<IdsWitheValue> answers;

}
