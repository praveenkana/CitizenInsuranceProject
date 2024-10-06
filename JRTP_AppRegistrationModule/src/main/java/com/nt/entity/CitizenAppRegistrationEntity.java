package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CITIZEN_APPLICATION")
public class CitizenAppRegistrationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer appId;
	@Column(length=40)
	private String fullName;
	@Column(length=40)
	private String email;
	@Column(length=40)
	private String gender;
	private Long phoneNo;
	private Integer ssn;
	@Column(length=40)
	private String stateName;
	@Column(length=40)
	private String BanKName;
	@Column(length=40)
	private String BranchName;
	private Long AccountNo;
	private LocalDate dob;
	@Column(length=40)
	private String createdBy;
	@Column(length=40)
	private String updatedBy;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdOn;
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDateTime uodatedOn;

}
