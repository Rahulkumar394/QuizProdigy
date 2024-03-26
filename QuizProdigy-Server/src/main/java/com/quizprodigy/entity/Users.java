package com.quizprodigy.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Users implements UserDetails{
	
	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "first_name",nullable = false)
	private String firstName;

	@Column(name = "last_name",nullable = false)
	private String lastName;

	@Column(name = "contact_number",unique =  true, nullable=false)
	private String contactNumber;

	@Column(name = "institute_name")
	private String instituteName;

	@Column(name = "enrollment",unique =  true)
	private String Enrollment;

	@Column(name = "status",nullable = false)
	private String status;

	@Column(name = "user_role",nullable = false)
	private String role;

	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "date_created",nullable = false)
	private Date createdDate;
	
	@Column(name = "date_modify")
	private Date modifyDate;
	
	@Column(name = "isdeleted",nullable = false)
	private boolean isDeleted;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role);
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		return userId;
	}

	// Methods for account expiration, locking, credentials expiration, and account status
    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
