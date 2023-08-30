package com.coursedataapi.config;

import com.coursedataapi.service.mappers.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	public CandidateServiceMapper candidateServiceMapper() {

		return Mappers.getMapper(CandidateServiceMapper.class);
	}

	@Bean
	public CourseServiceMapper courseServiceMapper() {

		return Mappers.getMapper(CourseServiceMapper.class);
	}

	@Bean
	public CourseAppliedServiceMapper courseAppliedServiceMapper() {

		return Mappers.getMapper(CourseAppliedServiceMapper.class);
	}

}
