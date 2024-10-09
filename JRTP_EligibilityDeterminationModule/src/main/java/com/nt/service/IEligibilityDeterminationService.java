package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.bindings.EligibilityDetailsOutput;

@Service
public interface IEligibilityDeterminationService {
	
	public EligibilityDetailsOutput determineEligigbility(Integer caseNo);

}
