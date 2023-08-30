package com.coursedataapi.service.mappers;

import com.coursedataapi.repository.api.CourseDO;
import com.coursedataapi.resource.api.CourseRO;
import org.mapstruct.Mapper;

@Mapper(uses = { CourseServiceMapper.class })
public interface CourseServiceMapper {

	CourseRO mapCourseDOToCourseRO(CourseDO courseDO);

	CourseDO mapCourseROToCourseDO(CourseRO courseRO);

}
