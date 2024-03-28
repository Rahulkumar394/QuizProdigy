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
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Students {

    
	@Id
	@Column(name = "student_id")//id will be email
	private String studentId;

	@Column(name = "student_name",nullable = false)
	private String studentName;

	@Column(name = "contact_no",unique =  true, nullable=false)
	private String contactNo;	
	
    @Column(name = "institute_name")
	private String instituteName;

	@Column(name = "enrollment",unique =  true)
	private String Enrollment;

	@Column(name = "status",nullable = false)
	private String status;

    @Column(name = "created_date",nullable = false)
	private Date createdDate;
	
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Column(name = "isdeleted",nullable = false)
	private boolean isDeleted;

	@Transient// Through this annotation  we can tell Hibernate not to persist this field.
    private String password;

    @OneToMany(mappedBy = "student")
    private List<Performance> performances;


}
