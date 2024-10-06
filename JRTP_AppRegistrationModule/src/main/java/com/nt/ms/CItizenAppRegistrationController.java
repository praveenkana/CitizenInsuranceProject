package com.nt.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nt.binding.CitizenAppRegistrationInputs;
import com.nt.service.ICitizenAppRegistrationService;
import com.nt.exceptions.InvalidSSNException;

@RestController
@RequestMapping("/citizen-api")
public class CItizenAppRegistrationController {
	@Autowired
	private ICitizenAppRegistrationService service;

	@PostMapping("/save")
	public ResponseEntity<String> registerCitizenApplication(@RequestBody CitizenAppRegistrationInputs inputs)
			throws InvalidSSNException {

		Integer appId = service.registerCitizenApplication(inputs);
		if (appId > 0)
			return new ResponseEntity<String>("citizen registered with " + appId, HttpStatus.OK);
		else
			return new ResponseEntity<String>("invalid ssn or citizen not from the required state ", HttpStatus.OK);

	}

}
