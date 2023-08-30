package com.coursedataapi.repository.api;

import com.coursedataapi.resource.api.CandidateRO;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;
import reactor.core.publisher.Flux;

@Value
@Builder
public class CandidateDO {

	@Column("candidateId")
	Long candidateId;

	@Column("candidateName")
	String candidateName;

	@Column("mobileNo")
	String mobileNo;

	@Column("emailId")
	String emailId;

	@Column("candidateRemark")
	String candidateRemark;
}
