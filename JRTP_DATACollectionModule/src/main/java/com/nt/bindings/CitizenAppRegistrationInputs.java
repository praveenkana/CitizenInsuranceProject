package com.nt.bindings;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CitizenAppRegistrationInputs {

	private String fullName;
	private String email;
	private String gender;
	private Long phoneNo;
	private Integer ssn;
	private String stateName;
	private String BanKName;
	private String BranchName;
	private Long AccountNo;
	private LocalDate dob;
}
