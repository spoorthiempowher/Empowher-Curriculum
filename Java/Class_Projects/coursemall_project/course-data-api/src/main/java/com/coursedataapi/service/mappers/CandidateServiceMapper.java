package com.coursedataapi.service.mappers;

import com.coursedataapi.repository.api.CandidateDO;
import com.coursedataapi.resource.api.CandidateRO;
import org.mapstruct.Mapper;

@Mapper(uses = { CandidateServiceMapper.class })
public interface CandidateServiceMapper {

	CandidateRO mapCandidateDOToCandidateRO(CandidateDO candidateDO);

	CandidateDO mapCandidateROToCandidateDO(CandidateRO candidateRO);

}
