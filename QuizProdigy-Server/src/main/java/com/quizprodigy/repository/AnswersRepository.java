package com.quizprodigy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Answers;
import com.quizprodigy.entity.Question;

@Repository
public interface AnswersRepository  extends JpaRepository<Answers, String>{

    Answers findByQuestion(Question question);

}
