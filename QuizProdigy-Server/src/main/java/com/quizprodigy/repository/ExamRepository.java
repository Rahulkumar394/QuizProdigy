package com.quizprodigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {
	
	Exam findByExamId(String examId);
    // Count all rows
    long count();

    // Get a list of examID by TeacherID
    @Query("SELECT e.examId FROM Exam e WHERE e.teacherId.teacherId = :teacherId")
    List<String> findExamIdByTeacherId(String teacherId);

    // Method to delete an exam by exam ID if delete then return true otherwise
    // false
    boolean deleteByExamId(String examId);
}
