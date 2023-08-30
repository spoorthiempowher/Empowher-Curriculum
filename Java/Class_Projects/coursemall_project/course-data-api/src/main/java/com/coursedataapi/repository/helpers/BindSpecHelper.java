package com.coursedataapi.repository.helpers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient.GenericExecuteSpec;
import org.springframework.r2dbc.core.FetchSpec;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public final class BindSpecHelper {

	private GenericExecuteSpec spec;

	public static BindSpecHelper createInstance(GenericExecuteSpec spec) {

		return new BindSpecHelper(spec);
	}

	public <T> BindSpecHelper bindIf(String name, T value, Class<T> clazz) {

		if (value == null) {
			this.spec = this.spec.bindNull(name, clazz);
		}
		else {
			this.spec = this.spec.bind(name, value);
		}

		return this;
	}

	public <T> BindSpecHelper bindStrictIf(String name, T value) {

		if (value != null) {
			this.spec = this.spec.bind(name, value);
		}

		return this;
	}

	public FetchSpec<Map<String, Object>> fetch() {

		return this.spec.fetch();
	}

	public Mono<Void> then() {

		return spec.then();
	}

	public Mono<Void> fetchAndValidateUpdatedRowCount(String queryName, Integer requiredUpdatedRowCount) {

		return this.spec.fetch()
				.rowsUpdated()
				.filter(requiredUpdatedRowCount::equals)
				.switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete/insert/update single row for " + queryName))))
				.then();
	}

}