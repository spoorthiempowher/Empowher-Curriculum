package com.coursedataapi.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationErrorDetails {

    private final OffsetDateTime timestamp;

    private final String traceId;

    private final List<Violation> violations = new ArrayList<>();

}
