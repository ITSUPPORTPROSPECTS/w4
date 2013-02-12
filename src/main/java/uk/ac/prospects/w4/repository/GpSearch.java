/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/
package uk.ac.prospects.w4.repository;

import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * Extends the {@link io.searchbox.core.Search} class to overide the {@code getURI} method and add the 'save' parameter to the URI that is used for the elastic search.
 *
 */
public class GpSearch extends Search {
	private int maxSearchResults;

	public GpSearch() {
		super();
	}

	public GpSearch(String query) {
		super(query);
	}

	public GpSearch(QueryBuilder query) {
		super(query);
	}

	@Override
	public String getURI() {
		StringBuilder stringBuilder = new StringBuilder(super.getURI());
		stringBuilder.append("?size=" + maxSearchResults);
		return stringBuilder.toString();
	}

	public int getMaxSearchResults() {
		return maxSearchResults;
	}

	public void setMaxSearchResults(int maxSearchResults) {
		this.maxSearchResults = maxSearchResults;
	}
}
