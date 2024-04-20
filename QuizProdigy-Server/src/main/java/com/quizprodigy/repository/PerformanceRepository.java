package com.quizprodigy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizprodigy.entity.Performance;

public interface PerformanceRepository  extends JpaRepository<Performance, String>{

}
