package com.nt.bindings;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EligibilityDetailsOutput {
	
	private String holderName;
	private Integer holderSSN;
	private String planName;
	private String planStatus;
	private LocalDateTime planStartDate;
	private LocalDateTime planEndDate;
	private Double benefitAmount;
	private String denialReason;
	

}
