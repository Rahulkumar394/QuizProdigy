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

	@Column(name = "department")
	private String department;

	@Column(name = "enrollment",unique =  true)
	private String enrollment;

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

    @Override
    public String toString() {
        return "Students{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", instituteName='" + instituteName + '\'' +
                ", department='" + department + '\'' +
                ", enrollment='" + enrollment + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", modifyDate=" + modifyDate +
                ", isDeleted=" + isDeleted +
                ", password=" + password +// Mask password for security
                ", performancesCount=" + (performances != null ? performances.size() : 0) +
                '}';
    }
}
