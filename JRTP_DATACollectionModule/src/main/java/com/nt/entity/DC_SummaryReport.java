package com.nt.entity;

import java.util.List;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanInputs;

import lombok.Data;

@Data
public class DC_SummaryReport {
	private String planName;
	private IncomeInputs incomeInputs;
	private EducationInputs educationInputs;
	private CitizenAppRegistrationInputs appInputs;
	private List<ChildrenInputs> childInputs;
	

}
