package com.coursedataapi.resource.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CandidateRO {

	Long candidateId;

	String candidateName;

	String mobileNo;

	String emailId;

	String candidateRemark;

}
