package com.coursedataapi.resource.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CourseRO {

	Long courseId;

	String courseName;

	Long coursePrice;

	String courseRemark;

}
