package com.quizprodigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Teachers;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String> {

    public List<Teachers> findAllTeachersByStatus(@Param("status") String status);

}
