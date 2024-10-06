package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nt.binding.CitizenAppRegistrationInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.exceptions.InvalidSSNException;
import com.nt.repository.ICitizenAppRegistrationRepository;

@Service
public class CitizenAppRegistrationServiceImpl implements ICitizenAppRegistrationService {
	@Autowired
	private ICitizenAppRegistrationRepository citizenrepo;
	@Autowired
	private RestTemplate template;
	//private WebClient client;

	@Value("${ar.web.url}")
	private String endPointUrl;
	@Value("${ar.state}")
	private String targetState;

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException {

		// input external web service url
		String url = endPointUrl;
		// perform web service call to check whether ssn is valid or not

		// String stateName = client.get().uri(url,
		// inputs.getSsn()).retrieve().bodyToMono(String.class).block();

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class, inputs.getSsn());
		String stateName = response.getBody();
		System.out.println(stateName);
		System.out.println(targetState);
		if (stateName.equalsIgnoreCase(targetState)) {
			// prepare entity object
			CitizenAppRegistrationEntity citizenEntity = new CitizenAppRegistrationEntity();
			BeanUtils.copyProperties(inputs, citizenEntity);
			citizenEntity.setStateName(stateName);
			// save the object
			Integer appId = citizenrepo.save(citizenEntity).getAppId();
			return appId;
		}

		throw new InvalidSSNException("invalinnd ssn");
	}

}
