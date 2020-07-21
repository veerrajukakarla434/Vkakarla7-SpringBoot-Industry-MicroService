package com.vkakarla.springboot.industry.microservice.service;

import com.vkakarla.springboot.industry.microservice.dto.IndustryResponse;

public interface IndustryService {
    
	 public IndustryResponse getActorsByIndustryName(String industryName);
	 
}
