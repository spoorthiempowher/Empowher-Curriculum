package com.coursedataapi.exception;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class DetailedErrorResponse<T> extends ErrorResponse {

	private final T details;

	public DetailedErrorResponse(OffsetDateTime timestamp, String traceId, String source, String message, T details) {

		super(timestamp, traceId, source, message);
		this.details = details;
	}

}
