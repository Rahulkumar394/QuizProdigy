package com.quizprodigy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizprodigy.entity.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, String> {
	public Users findByUserId(String userId);
}
