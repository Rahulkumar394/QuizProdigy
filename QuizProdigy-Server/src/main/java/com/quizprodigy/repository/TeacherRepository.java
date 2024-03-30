package com.quizprodigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizprodigy.entity.Teachers;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String> {

    public List<Teachers> findAllTeachersByStatus(@Param("status") String status);

    // Through this method we update the status of the Teacher to Accepted or rejected
	// from Pending
	@Modifying
	@Transactional
	@Query("UPDATE Teachres t SET t.status = :status WHERE t.teacherId = :email")
	void updateStatusByShopEmail(String email, String status);


}
