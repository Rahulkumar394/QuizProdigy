package com.quizprodigy.dto;

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
public class ExamScheduleDTO {
    private String examId;
    private String examName;
    // private int totalQuestion;
    private String teacherName;
}
