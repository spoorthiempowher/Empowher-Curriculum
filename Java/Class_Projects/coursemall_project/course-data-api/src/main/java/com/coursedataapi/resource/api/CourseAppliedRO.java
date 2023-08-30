package com.coursedataapi.resource.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CourseAppliedRO {

	Long candidateId;

	Long courseId;

	String courseAppliedRemark;

}
