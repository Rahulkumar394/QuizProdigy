package com.quizprodigy.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Teachers {

	@Id
	@Column(name = "teacher_id") // id will by email
	private String teacherId;

	@Column(name = "teacher_name", nullable = false)
	private String teacherName;

	@Column(name = "department", nullable = false)
	private String department;

	@Column(name = "contact_no", unique = true, nullable = false)
	private String contactNo;

	@Column(name = "institute_name")
	private String instituteName;

	@Column(name = "employee_no", unique = true)
	private String employeeNo;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "isdeleted", nullable = false)
	private boolean isDeleted;

	@Transient // Through this annotation we can tell Hibernate not to persist this field.
	private String password;

	// @OneToMany(mappedBy = "teacherId")
	// private List<Exam> exams;

	// public Teachers(String teacherId) {
	// 	this.teacherId = teacherId;
	// }

	@Override
	public String toString() {
		return "Teachers{" +
				"teacherId='" + teacherId + '\'' +
				", teacherName='" + teacherName + '\'' +
				", department='" + department + '\'' +
				", contactNo='" + contactNo + '\'' + // Include full contact number for this example (adjust as needed)
				", instituteName='" + instituteName + '\'' +
				", employeeNo='" + employeeNo + '\'' +
				", status='" + status + '\'' +
				", createdDate=" + createdDate +
				", modifyDate=" + modifyDate +
				", isDeleted=" + isDeleted +
				", password=" + password + // Mask password for security
//", examsCount=" + (exams != null ? exams.size() : 0) +
				'}';
	}

}
