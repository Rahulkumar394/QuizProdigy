package com.quizprodigy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, String> {

}
