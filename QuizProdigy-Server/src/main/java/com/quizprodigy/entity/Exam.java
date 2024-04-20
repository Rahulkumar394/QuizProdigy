package com.quizprodigy.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Exam {

    @Id
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "total_questions", nullable = false)
    private int totalQuestions;

    @Column(name = "total_time", nullable = false)
    private int totalTime;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "is_hands_on")
    private boolean isHandsOn;

    // through this we can set exam date
    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "exam_time")
    private String examTime;

    // through this we can start exam
    @Column(name = "isStart")
    private boolean isStart;

    // through this column we can check exam status exam done or not
    @Column(name = "exam_status")
    private String examStatus;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "cut_off_marks")
    private float cutOffMarks;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Exclude this field from JSON serialization
    private List<Question> questions;

    @Override
    public String toString() {
        String questionsCount = questions != null ? String.valueOf(questions.size()) : "0";
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", totalQuestions=" + totalQuestions +
                ", totalTime=" + totalTime +
                ", createdDate=" + createdDate +
                ", modifyDate=" + modifyDate +
                ", isDelete=" + isDelete +
                ", isHandsOn=" + isHandsOn +
                ", examDate=" + examDate +
                ", isStart=" + isStart +
                ", examStatus=" + examStatus +
                ", teacherId='" + teacherId +
                ", cutOffMarks=" + cutOffMarks +
                ", questionsCount=" + questionsCount +
                '}';
    }

}
