package com.quizprodigy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {

    // Count all rows 
    long count(); 
}
