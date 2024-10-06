package com.nt.ms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssn-web-api")
public class SSNVerifyingController {

	@GetMapping("/find/{ssn}")
	public ResponseEntity<String> validateSSN(@PathVariable Integer ssn) {

		if (String.valueOf(ssn).length() != 9) {
			return new ResponseEntity<String>("invalid ssn", HttpStatus.BAD_REQUEST);
		}

		int stateCode = ssn % 100;
		String stateName = null;
		if (stateCode == 1)
			stateName = "california";
		else if (stateCode == 2)
			stateName = "ohio";
		else if (stateCode == 3)
			stateName = "washington";
		else if (stateCode == 4)
			stateName = "newyork";
		else if (stateCode == 5)
			stateName = "alabama";
		else
			stateName = "invalid ssn";
		return new ResponseEntity<String>(stateName, HttpStatus.OK);

	}
}
