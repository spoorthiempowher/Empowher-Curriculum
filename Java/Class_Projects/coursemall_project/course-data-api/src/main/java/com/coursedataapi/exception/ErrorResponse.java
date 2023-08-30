package com.coursedataapi.exception;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

	private final OffsetDateTime timestamp;

	private final String traceId;

	private final String source;

	private final String message;

}
