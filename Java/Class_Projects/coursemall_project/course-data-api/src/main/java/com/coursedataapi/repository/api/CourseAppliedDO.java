package com.coursedataapi.repository.api;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;

@Value
@Builder
public class CourseAppliedDO {

	@Column("candidateId")
	Long candidateId;

	@Column("courseId")
	Long courseId;

	@Column("courseAppliedRemark")
	String courseAppliedRemark;

}
