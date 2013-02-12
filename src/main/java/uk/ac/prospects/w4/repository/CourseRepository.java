/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/
package uk.ac.prospects.w4.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * Builds the query based on the search criteria and queries the XCRI CAP Aggregator using Jest as the client for the ElasticSearch Http Rest interface.
 *
 */
@Component
public class CourseRepository {

	private String server;
	private String index;
	private String type;

	public void setServer(String server) {
		this.server = server;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Queries the XCRI CAP aggregator using JEST as the elastic search client. <br/>
	 * The query is constructed based on the {@code argument} passed to the method.
	 *
	 * @param argument The argument that holds the search criteria ({@link uk.ac.prospects.w4.repository.CourseSearchArgument})
	 * @return
	 */
	public String findAllCourses(CourseSearchArgument argument){

		// Configuration
		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(server);
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);

		// Construct a new Jest client according to configuration via factory
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig);
		JestClient client = factory.getObject();

		QueryBuilder queryBuilder = buildQuery(argument);

		GpSearch search = new GpSearch(queryBuilder);
		search.addIndex(index);
		search.addType(type);
		search.setMaxSearchResults(argument.getMaxResults());

		JestResult result = null;
		try {
			result = client.execute(search);
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		return result.getJsonString();
	}

	private QueryBuilder buildQuery(CourseSearchArgument argument){
		StringBuilder sb = new StringBuilder();

		// Add the provider or get all courses that have a one
		sb.append("provid:" + (StringUtils.hasText(argument.getProviderId()) ? argument.getProviderId() : "*"));

		if (argument.isExcludeEmptyStartDates()) {
		//Only courses that have a presentation start date will be returned
			sb.append(" AND presentations.start:" + (StringUtils.hasText(argument.getStartDate()) ? argument.getStartDate() : "*"));
		} else {
			if (StringUtils.hasText(argument.getStartDate())){
				sb.append(" AND presentations.start:" +  argument.getStartDate());
			}
		}

		if (StringUtils.hasText(argument.getCourseTitle())){
			sb.append(" AND title:" +  argument.getCourseTitle());
		}
		if (StringUtils.hasText(argument.getProviderTitle())){
			sb.append(" AND provtitle:" + argument.getProviderTitle());
		}
		if (StringUtils.hasText(argument.getFromStartDate()) || StringUtils.hasText(argument.getToStartDate())) {
			sb.append(" AND presentations.start:[");
			sb.append(StringUtils.hasText(argument.getFromStartDate()) ? "\"" + argument.getFromStartDate() + "\"" : "*");
			sb.append(" TO ");
			sb.append(StringUtils.hasText(argument.getToStartDate()) ? "\"" + argument.getToStartDate() + "\"" : "*");
			sb.append("]");
		}

		//To search for the keyword in all fields
		if (StringUtils.hasText(argument.getKeyword())){
			sb.append(" AND " + argument.getKeyword());
		}

		QueryBuilder queryBuilder = QueryBuilders.queryString(sb.toString());
		return queryBuilder;
	}
}
