package com.nt.service;

import java.util.List;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanInputs;
import com.nt.entity.DC_SummaryReport;

public interface ICollectionMgmtService  {
	
	public Integer generateCaseNo(Integer appId);
	public Integer savePlanSelection(PlanInputs planInputs);
	public Integer saveIncomeDetails(IncomeInputs income);
	public Integer saveEducationDetails(EducationInputs edInputs) ;   
	public List<String> showAllPlans();
	public Integer saveChildrenDetails(List<ChildrenInputs> children);
	public DC_SummaryReport showDC_SummaryReport(Integer caseNo);
}
