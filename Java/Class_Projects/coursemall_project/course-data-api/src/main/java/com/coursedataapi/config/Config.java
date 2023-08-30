package com.coursedataapi.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.DialectResolver;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.mapping.OutboundRow;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Row;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Config {

	@Bean
	public R2dbcCustomConversions r2dbcCustomConversions(ConnectionFactory connectionFactory,
														 Collection<Converter<Row, ?>> inboundConverters, Collection<Converter<?, OutboundRow>> outboundConverters) {

		R2dbcDialect dialect = DialectResolver.getDialect(connectionFactory);
		List<Object> converters = new ArrayList<>(dialect.getConverters());
		converters.addAll(R2dbcCustomConversions.STORE_CONVERTERS);
		return new R2dbcCustomConversions(
				CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder(), converters),
				Stream.concat(inboundConverters.stream(), outboundConverters.stream())
						.toList());
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {

		return new InMemoryHttpTraceRepository() {

			@Override
			public void add(HttpTrace trace) {

				try {
					log.info("user={} method={} uri={} status={} referer_url={} req_tie={}",
							 traceUser(trace),
							 traceMethod(trace),
							 traceUri(trace),
							 traceStatus(trace),
							 traceReferer(trace),
							 traceTime(trace));
				}
				catch (URISyntaxException e) {
					log.error("invalid URI {}. {}", trace.getRequest()
							.getUri(), e);
				}
				super.add(trace);
			}

		};
	}

	private String traceUser(HttpTrace trace) {

		return trace.getPrincipal() == null ? "-" : trace.getPrincipal()
				.getName();
	}

	private String traceMethod(HttpTrace trace) {

		return trace.getRequest()
				.getMethod();
	}

	private URI traceUri(HttpTrace trace) throws URISyntaxException {

		return new URI(null, null, trace.getRequest()
				.getUri()
				.getPath(),
					   trace.getRequest()
							   .getUri()
							   .getQuery(), trace.getRequest()
							   .getUri()
							   .getFragment());
	}

	private int traceStatus(HttpTrace trace) {

		return trace.getResponse()
				.getStatus();
	}

	private String traceReferer(HttpTrace trace) {

		return trace.getRequest()
				.getHeaders()
				.getOrDefault("Referer", List.of("-"))
				.get(0);
	}

	private String traceTime(HttpTrace trace) {

		return trace.getTimeTaken() + "ms";
	}

}
