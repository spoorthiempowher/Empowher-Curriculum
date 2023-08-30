package com.coursedataapi.repository.api;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;

@Value
@Builder
public class CourseDO {

	@Column("courseId")
	Long courseId;

	@Column("courseName")
	String courseName;

	@Column("coursePrice")
	Long coursePrice;

	@Column("courseRemark")
	String courseRemark;

}
