package com.coursedataapi.resource;

import com.coursedataapi.CandidateTestDataFactory;
import com.coursedataapi.resource.api.CandidateRO;
import com.coursedataapi.service.CourseService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WithMockUser
@ExtendWith(MockitoExtension.class)
@WebFluxTest(CourseController.class)
class CourseControllerTests {

	@Autowired
	private WebTestClient client;

	@MockBean
	private CourseService mockCourseService;

	@Captor
	private ArgumentCaptor<Long> candidateIdCaptor;

	private final CandidateTestDataFactory candidateTestDataFactory = new CandidateTestDataFactory();

	// =================================================================================================================
	// Candidate
	// =================================================================================================================

	@Test
	public void retrieveCandidate_works() {

		// Given
		Long anyCandidateId = candidateTestDataFactory.getModelObject().getCandidateId();
		CandidateRO anyCandidateRO = candidateTestDataFactory.getResourceObject();

		when(mockCourseService.retrieveCandidateByCandidateId(candidateIdCaptor.capture())).thenReturn(Mono.just(anyCandidateRO));

		// When
		client.get()
				.uri(uriBuilder -> uriBuilder
						.path("/courseMall/candidate/{candidateId}")
						.build(anyCandidateId))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(CandidateRO.class)
				.contains(anyCandidateRO);

		// Then
		assertEquals(anyCandidateId, candidateIdCaptor.getValue());

	}

	@SneakyThrows(IOException.class)
	static String mockContent(String bodyContentFile) {

		return FileCopyUtils.copyToString(
				new InputStreamReader(new ClassPathResource("mocks/" + bodyContentFile).getInputStream()));
	}

}
