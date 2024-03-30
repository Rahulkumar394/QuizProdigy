package com.quizprodigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, String> {

    public List<Students> findAllStudentsByStatus(@Param("status") String status);
}
