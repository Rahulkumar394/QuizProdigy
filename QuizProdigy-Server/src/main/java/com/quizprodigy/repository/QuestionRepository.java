package com.quizprodigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Exam;
import com.quizprodigy.entity.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question , String>{

    List<Question> findByExam(Exam exam);

}


