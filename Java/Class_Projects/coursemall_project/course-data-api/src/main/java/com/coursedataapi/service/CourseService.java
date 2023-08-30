package com.coursedataapi.service;

import com.coursedataapi.repository.CourseRepository;
import com.coursedataapi.repository.CourseUpdateRepository;
import com.coursedataapi.resource.api.CandidateRO;
import com.coursedataapi.resource.api.CourseRO;
import com.coursedataapi.service.mappers.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;

	private final CandidateServiceMapper candidateServiceMapper;

	private final CourseServiceMapper courseServiceMapper;

	private final CourseUpdateRepository courseUpdateRepository;

	// =================================================================================================================
	// Candidates
	// =================================================================================================================

	public Flux<CandidateRO> retrieveCandidates() {

		return courseRepository.retrieveCandidates()
				.map(candidateServiceMapper::mapCandidateDOToCandidateRO);
	}

	public Mono<CandidateRO> retrieveCandidateByCandidateId(Long candidateId) {

		return courseRepository.retrieveCandidateByCandidateId(candidateId)
				.map(candidateServiceMapper::mapCandidateDOToCandidateRO);
	}

	// =================================================================================================================
	// Courses
	// =================================================================================================================

	public Flux<CourseRO> retrieveCourses() {

		return courseRepository.retrieveCourses()
				.map(courseServiceMapper::mapCourseDOToCourseRO);
	}

	public Mono<CourseRO> retrieveCourseByCourseId(Long courseId) {

		return courseRepository.retrieveCourseByCourseId(courseId)
				.map(courseServiceMapper::mapCourseDOToCourseRO);
	}

	// =================================================================================================================
	// CourseApplied
	// =================================================================================================================




	// =================================================================================================================
	// Create: Candidate
	// =================================================================================================================

	public Mono<CandidateRO> createCandidate(Mono<CandidateRO> candidateROMono) {

		Mono<CandidateRO> cachedCandidate = candidateROMono.cache();

		return cachedCandidate.map(candidateServiceMapper::mapCandidateROToCandidateDO)
				.flatMap(courseUpdateRepository::insertCandidate)
				.flatMap(this::retrieveCandidateByCandidateId);

	}

	// =================================================================================================================
	// Create: Course
	// =================================================================================================================

	public Mono<CourseRO> createCourse(Mono<CourseRO> courseROMono) {

		Mono<CourseRO> cachedComment = courseROMono.cache();

		return cachedComment.map(courseServiceMapper::mapCourseROToCourseDO)
				.flatMap(courseUpdateRepository::insertCourse)
				.flatMap(this::retrieveCourseByCourseId);

	}

	// =================================================================================================================
	// Create: CourseApplied
	// =================================================================================================================



	// =================================================================================================================
	// Update: Candidate
	// =================================================================================================================

	public Mono<Void> updateCandidate(Long candidateId, Mono<CandidateRO> candidateROMono) {

		Mono<CandidateRO> cachedCandidate = candidateROMono.cache();

		return courseUpdateRepository.deleteCandidate(candidateId)
				.then(cachedCandidate.map(candidateServiceMapper::mapCandidateROToCandidateDO))
				.flatMap(candidateDO -> courseUpdateRepository.insertCandidate(candidateId, candidateDO))
				.then();

	}

	// =================================================================================================================
	// Update: Course
	// =================================================================================================================

	public Mono<Void> updateCourse(Long courseId, Mono<CourseRO> courseROMono) {

		Mono<CourseRO> cachedCourse = courseROMono.cache();

		return courseUpdateRepository.deleteCourse(courseId)
				.then(cachedCourse.map(courseServiceMapper::mapCourseROToCourseDO))
				.flatMap(courseDO -> courseUpdateRepository.insertCourse(courseId, courseDO))
				.then();

	}

	// =================================================================================================================
	// Update: CourseApplied
	// =================================================================================================================



	// =================================================================================================================
	// Delete: Candidate
	// =================================================================================================================

	public Mono<Void> deleteCandidate(Long candidateId) {

		return courseUpdateRepository.deleteCandidate(candidateId);

	}

	// =================================================================================================================
	// Delete: Course
	// =================================================================================================================

	public Mono<Void> deleteCourse(Long courseId) {

		return courseUpdateRepository.deleteCourse(courseId);

	}

	// =================================================================================================================
	// Delete: CourseApplied
	// =================================================================================================================



}
