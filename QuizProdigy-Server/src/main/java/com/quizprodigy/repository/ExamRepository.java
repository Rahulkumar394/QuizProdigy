package com.quizprodigy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizprodigy.dto.ExamScheduleDTO;
import com.quizprodigy.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {

    Exam findByExamId(String examId);

    // Count all rows
    long count();

    // Get a list of examID by TeacherID
    @Query("SELECT e.examId FROM Exam e WHERE e.teacherId = :teacherId")
    List<String> findExamIdByTeacherId(String teacherId);

    
    // Method to delete an exam by exam ID if delete then return true otherwise
    // false
    @Transactional
    @Modifying
    Integer deleteByExamId(String examId);

    // Schedule date and time through examid
    @Modifying
    @Transactional
    @Query("UPDATE Exam e SET e.examDate = :examDate, e.examTime = :time WHERE e.examId = :examId")
    Integer updateExamDateByExamId(@Param("examId") String examId, @Param("examDate")Date examDate, @Param("time")String time);

    @Modifying
    @Transactional
    @Query("UPDATE Exam e SET e.examStatus = 'STARTED', e.isStart= true WHERE e.examId = :examId")
    Integer updateExamStatusByExamId(String examId);

    // @Query("SELECT new com.quizprodigy.dto.ExamScheduleDTO(e.examId, e.subjectName, e.teacherId.teacherName) " +
    //         "FROM Exam e WHERE e.examStatus = 'NOT STARTED' AND e.isStart = false " )
    // @Query("SELECT new com.quizprodigy.dto.ExamScheduleDTO(e.examId, e.subjectName, e.teacherId.teacherName) " +
    //    "FROM Exam e WHERE e.examStatus = 'NOT STARTED' AND e.isStart = false " +
    //    "AND e.examDate IS NULL AND e.examTime IS  NULL")
    @Query("SELECT new com.quizprodigy.dto.ExamScheduleDTO(e.examId, e.subjectName, e.teacherId) " +
    "FROM Exam e WHERE e.examStatus = 'NOT STARTED' AND e.isStart = false " +
    "AND e.examDate IS NULL")
    List<ExamScheduleDTO> findNotStartedExams();
}
