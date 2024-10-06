package com.nt.binding;

import java.time.LocalDate;


import lombok.Data;

@Data
public class CitizenAppRegistrationInputs {
	
	private String fullName;
	private String email;
	private String gender;
	private Long phoneNo;
	private Integer ssn;
	private String stateName;
	private LocalDate dob;
	private String BanKName;
	private String BranchName;
	private Long AccountNo;

}
