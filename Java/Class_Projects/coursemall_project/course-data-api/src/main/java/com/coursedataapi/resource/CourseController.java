package com.coursedataapi.resource;

import com.coursedataapi.resource.api.*;
import com.coursedataapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courseMall")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

	private final CourseService courseService;

	// =================================================================================================================
	// Candidates
	// =================================================================================================================

	@GetMapping(path = "/candidates")
	public Flux<CandidateRO> retrieveCandidates() {

		return courseService.retrieveCandidates();

	}

	@GetMapping(path = "/candidate/{candidateId}")
	public Mono<CandidateRO> retrieveCandidateByCandidateId(@PathVariable Long candidateId) {

		return courseService.retrieveCandidateByCandidateId(candidateId);

	}

	// =================================================================================================================
	// Courses
	// =================================================================================================================

	@GetMapping(path = "/courses")
	public Flux<CourseRO> retrieveCourses() {

		return courseService.retrieveCourses();

	}

	@GetMapping(path = "/course/{courseId}")
	public Mono<CourseRO> retrieveCourseByCourseId(@PathVariable Long courseId) {

		return courseService.retrieveCourseByCourseId(courseId);

	}

	// =================================================================================================================
	// CourseApplied
	// =================================================================================================================



	// =================================================================================================================
	// Create: Candidate
	// =================================================================================================================

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/newCandidate")
	public Mono<CandidateRO> createCandidate(@RequestBody Mono<CandidateRO> candidateROMono) {

		return courseService.createCandidate(candidateROMono);

	}

	// =================================================================================================================
	// Create: Course
	// =================================================================================================================

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/newCourse")
	public Mono<CourseRO> createCourse(@RequestBody Mono<CourseRO> courseROMono) {

		return courseService.createCourse(courseROMono);

	}

	// =================================================================================================================
	// Create: CourseApplied
	// =================================================================================================================



	// =================================================================================================================
	// Update: Candidate
	// =================================================================================================================

	@PutMapping(path = "/{candidateId}/candidate")
	public Mono<Void> updateCandidate(@PathVariable Long candidateId,
											 @RequestBody Mono<CandidateRO> candidateROMono) {

		return courseService.updateCandidate(candidateId, candidateROMono);

	}

	// =================================================================================================================
	// Update: Course
	// =================================================================================================================

	@PutMapping(path = "/{courseId}/course")
	public Mono<Void> updateCourse(@PathVariable Long courseId,
										  @RequestBody Mono<CourseRO> courseROMono) {

		return courseService.updateCourse(courseId, courseROMono);

	}

	// =================================================================================================================
	// Update: CourseApplied
	// =================================================================================================================



	// =================================================================================================================
	// Delete: Candidate
	// =================================================================================================================

	@DeleteMapping(path = "/{candidateId}/candidate")
	public Mono<Void> deleteCandidate(@PathVariable Long candidateId) {

		return courseService.deleteCandidate(candidateId);

	}

	// =================================================================================================================
	// Delete: Course
	// =================================================================================================================

	@DeleteMapping(path = "/{courseId}/course")
	public Mono<Void> deleteCourse(@PathVariable Long courseId) {

		return courseService.deleteCourse(courseId);

	}

	// =================================================================================================================
	// Delete: CourseApplied
	// =================================================================================================================



}
