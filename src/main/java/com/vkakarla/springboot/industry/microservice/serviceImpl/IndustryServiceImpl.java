package com.vkakarla.springboot.industry.microservice.serviceImpl;

import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vkakarla.springboot.industry.microservice.config.IndustryServiceConfig;
import com.vkakarla.springboot.industry.microservice.dto.Actors;
import com.vkakarla.springboot.industry.microservice.dto.IndustryResponse;
import com.vkakarla.springboot.industry.microservice.service.IndustryService;

@Service
public class IndustryServiceImpl implements IndustryService {

	@Autowired
	IndustryServiceConfig industryServiceConfig;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper mapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public IndustryResponse getActorsByIndustryName(String industryName) {
		
		String Url = getActorsServiceApiURL(industryName);
		
		ResponseEntity<Object> restResponse = null;
		
		List<Actors> actorsResponse = null;
		
		IndustryResponse industryResponse = new IndustryResponse();
		
		restResponse = restTemplate.getForEntity(Url, Object.class);
		
		actorsResponse = mapper.convertValue(restResponse.getBody(), ArrayList.class);
		
		industryResponse.setIndustryName(industryName);
		industryResponse.setActorsList(actorsResponse);
		
		return industryResponse;
	}
	
	
	private String getActorsServiceApiURL(String industryName) {
		UriComponentsBuilder uriBuilder = null;

		if (!Strings.isBlank(industryName)) {
			uriBuilder = UriComponentsBuilder.fromUriString(format(industryServiceConfig.getActorServiceUrl(), industryName));
		}

		return uriBuilder.build().encode().toUriString();

	}
	

}
