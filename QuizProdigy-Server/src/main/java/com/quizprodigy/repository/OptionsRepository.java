package com.quizprodigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Options;
import com.quizprodigy.entity.Question;

@Repository
public interface OptionsRepository  extends JpaRepository<Options, String>{

	List<Options> findByQuestionId(Question questionId);
//	List<Options> findByQuestionId(String questionId);
    //List<Options> findByQuestion(String questionId);

}
