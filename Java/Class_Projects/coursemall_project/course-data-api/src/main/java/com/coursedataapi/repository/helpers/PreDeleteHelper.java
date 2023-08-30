package com.coursedataapi.repository.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class PreDeleteHelper {

	@Autowired
	private DatabaseClient databaseClient;

	public Mono<Void> runPreDelete(String tableName) {

		var tempTableName = String.format("%s_temp", tableName);
		var createSql = String.format("CREATE TEMP TABLE IF NOT EXISTS %s (user_logon_nm varchar(50), curnt_dtm timestamp with time zone)", tempTableName);

		return this.databaseClient
				.sql(createSql)
				.fetch()
				.rowsUpdated()
				.then();
	}

}