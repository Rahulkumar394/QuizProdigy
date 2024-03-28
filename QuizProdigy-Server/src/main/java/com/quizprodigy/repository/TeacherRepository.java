package com.quizprodigy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Teachers;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String> {

}
