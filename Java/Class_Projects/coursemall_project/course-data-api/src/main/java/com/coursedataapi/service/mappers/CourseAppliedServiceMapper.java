package com.coursedataapi.service.mappers;

import com.coursedataapi.repository.api.CourseAppliedDO;
import com.coursedataapi.resource.api.CourseAppliedRO;
import org.mapstruct.Mapper;

@Mapper(uses = { CourseAppliedServiceMapper.class })
public interface CourseAppliedServiceMapper {

	CourseAppliedRO mapCourseAppliedDOToCourseAppliedRO(CourseAppliedDO courseAppliedDO);

	CourseAppliedDO mapCourseAppliedROToCourseAppliedDO(CourseAppliedRO courseAppliedRO);

}
