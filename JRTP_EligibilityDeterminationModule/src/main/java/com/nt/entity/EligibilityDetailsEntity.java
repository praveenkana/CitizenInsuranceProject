package com.nt.entity;

import java.time.LocalDateTime;

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
@Table
public class EligibilityDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer edTraceId;
	private Integer caseNo;
	private String holderName;
	private Integer holderSSN;
	private String planName;
	private String planStatus;
	private LocalDateTime planStartDate;
	private LocalDateTime planEndDate;
	private Double benefitAmount;
	private String denialReason;

}
