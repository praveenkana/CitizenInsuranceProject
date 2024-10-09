package com.nt.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nt.bindings.EligibilityDetailsOutput;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.entity.DC_CaseEntity;
import com.nt.entity.DC_IncomeEntity;
import com.nt.entity.PlanEntity;
import com.nt.repository.ICitizenAppRegistrationRepository;
import com.nt.repository.IDC_CaseRepository;
import com.nt.repository.IDC_IncomeRepository;
import com.nt.repository.IDC_PlanRepository;

public class EligibilityDeterminationServiceImpl implements IEligibilityDeterminationService {

	@Autowired
	private IDC_CaseRepository caserepo;
	@Autowired
	private IDC_PlanRepository planrepo;
	@Autowired
	private ICitizenAppRegistrationRepository apprepo;
	@Autowired
	private IDC_IncomeRepository incomerepo;
	
	@Override
	public EligibilityDetailsOutput determineEligigbility(Integer caseNo) {
		// get appid and planid based caseno;
		Integer planId = null;
		Integer appId = null;
		String planName = null;
		Period citizenAge;
		Integer holderSSN = 0;
		String name = null;
		Optional<DC_CaseEntity> opt = caserepo.findById(caseNo);
		if (opt.isPresent()) {
			DC_CaseEntity caseEntity = opt.get();
			planId = caseEntity.getPlanId();
			appId = caseEntity.getAppId();
		}

		Optional<PlanEntity> opt1 = planrepo.findById(planId);
		PlanEntity planEntity = opt1.get();
		planName = planEntity.getPlanName();

		Optional<CitizenAppRegistrationEntity> opt3 = apprepo.findById(appId);
		if (opt3.isPresent()) {
			CitizenAppRegistrationEntity appentity = opt3.get();
			name = appentity.getFullName();
			LocalDate dob = appentity.getDob();
			LocalDate systime = LocalDate.now();
			citizenAge = Period.between(systime, dob);

		}

		return null;
	}

	private EligibilityDetailsOutput deterimneEligibility(Integer caseNo, String planName, Period age) {
		
		EligibilityDetailsOutput elgioutput = new EligibilityDetailsOutput();
		elgioutput.setPlanName(planName);
		
		Optional<DC_IncomeEntity> opt = incomerepo.findById(caseNo);
		if(opt.isPresent()) {
			DC_IncomeEntity incomeEntity = opt.get();
			Double empIncome = incomeEntity.getEmpIncome();
			Double propertyIncome = incomeEntity.getPropertyIncome();
			
			if(planName.equalsIgnoreCase("SNAP"))
				if(empIncome<300) {
					elgioutput.setPlanStatus("Approved");
					elgioutput.setBenefitAmount(200.0);
					
				}else {
					elgioutput.setPlanStatus("denied");
					elgioutput.setDenialReason("high_income");
				}else if(planName.equalsIgnoreCase("CCAP")) {
					
					
					
				}
			if(elgioutput.getPlanStatus().equalsIgnoreCase("Approved")) {
				elgioutput.setPlanStartDate(LocalDateTime.now());
				elgioutput.setPlanEndDate(LocalDateTime.now().plusYears(3));
				
			}
			
		}
		return elgioutput;
		

	}
}
