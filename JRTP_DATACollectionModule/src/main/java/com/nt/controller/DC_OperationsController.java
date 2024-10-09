package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanInputs;
import com.nt.entity.DC_SummaryReport;
import com.nt.service.ICollectionMgmtService;

@RestController
@RequestMapping("/dc-api")
public class DC_OperationsController {

	@Autowired
	private ICollectionMgmtService service;

	@GetMapping("/plan-name")
	public ResponseEntity<List<String>> showAllPlanNames() {
		List<String> plans = service.showAllPlans();
		return new ResponseEntity<List<String>>(plans, HttpStatus.OK);
	}

	@PostMapping("/generate-caseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNo(@PathVariable Integer appId) {
		Integer caseNo = service.generateCaseNo(appId);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}

	@PutMapping("/update-planSelection")
	public ResponseEntity<Integer> updateSelectedPlan(@RequestBody PlanInputs inputs) {
		Integer caseNo = service.savePlanSelection(inputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}

	@PostMapping("/save-children")
	public ResponseEntity<Integer> saveChildren(@RequestBody List<ChildrenInputs> inputs) {
		Integer caseNo = service.saveChildrenDetails(inputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}

	@PostMapping("/save-education")
	public ResponseEntity<Integer> submitEducationDetails(@RequestBody EducationInputs edinputs) {
		Integer caseNo = service.saveEducationDetails(edinputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}

	@PostMapping("/save-income")
	public ResponseEntity<Integer> submitIncomeDetails(@RequestBody IncomeInputs inputs) {
		Integer caseNo = service.saveIncomeDetails(inputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}

	@GetMapping("/report/{caseNo}")
	public ResponseEntity<DC_SummaryReport> showReport(@PathVariable Integer caseNo) {

		DC_SummaryReport report = service.showDC_SummaryReport(caseNo);

		return new ResponseEntity<DC_SummaryReport>(report, HttpStatus.OK);
	}

}
