package com.coursedataapi.repository;

import com.coursedataapi.repository.api.*;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<CourseDO, Long> {

	final class Queries {

		private Queries() {

		}

		// =============================================================================================================
		// Candidates
		// =============================================================================================================

		private static final String SELECT_QUERY_CANDIDATES = """
														  SELECT candidate.* 
														  FROM wiki.candidate candidate
														  """;

		private static final String SELECT_QUERY_SPECIFIC_CANDIDATE = """
																   SELECT candidate.* 
																   FROM wiki.candidate candidate
																   """;

		private static final String ORDER_BY_CANDIDATEID = """
															 Order By candidate."candidateId"
															 """;

		// =============================================================================================================
		// Courses
		// =============================================================================================================

		private static final String SELECT_QUERY_COURSES = """
														  SELECT course.* 
														  FROM wiki.course course
														  """;

		private static final String SELECT_QUERY_SPECIFIC_COURSE = """
																   SELECT course.* 
																   FROM wiki.course course
																   """;

		private static final String ORDER_BY_COURSEID = """
															 Order By course."courseId"
															 """;

		// =================================================================================================================
		// CourseApplied
		// =================================================================================================================



	}

	@Query(Queries.SELECT_QUERY_CANDIDATES + Queries.ORDER_BY_CANDIDATEID)
	Flux<CandidateDO> retrieveCandidates();

	@Query(Queries.SELECT_QUERY_SPECIFIC_CANDIDATE + " WHERE candidate.\"candidateId\" = :candidateId ")
	Mono<CandidateDO> retrieveCandidateByCandidateId(Long candidateId);

	@Query(Queries.SELECT_QUERY_COURSES + Queries.ORDER_BY_COURSEID)
	Flux<CourseDO> retrieveCourses();

	@Query(Queries.SELECT_QUERY_SPECIFIC_COURSE + " WHERE course.\"courseId\" = :courseId ")
	Mono<CourseDO> retrieveCourseByCourseId(Long courseId);

}
