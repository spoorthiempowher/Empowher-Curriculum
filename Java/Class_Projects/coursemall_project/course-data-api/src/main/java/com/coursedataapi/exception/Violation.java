package com.coursedataapi.exception;

import javax.validation.Path;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {

	private final String fieldName;
	private final String message;

	public Violation(Path fieldPath, String message) {

		this.fieldName = fieldPath.toString();
		this.message = message;

	}

}
