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
 * Extends the {@link io.searchbox.core.Search} class to overide the {@code getURI} method and add the 'size' parameter to the URI that is used for the elastic search.
 *
 */
public class GpSearch extends Search {
	private int maxSearchResults;

	/** Calls super constructor. */
	public GpSearch() {
		super();
	}

	/** Calls super constructor. */
	public GpSearch(String query) {
		super(query);
	}

	/** Calls super constructor. */
	public GpSearch(QueryBuilder query) {
		super(query);
	}

	/** 
	 * Get the URI with a size parameter appended.
	 * @return the URI
	 */
	@Override
	public String getURI() {
		StringBuilder stringBuilder = new StringBuilder(super.getURI());
		stringBuilder.append("?size=" + maxSearchResults);
		return stringBuilder.toString();
	}

	public int getMaxSearchResults() {
		return maxSearchResults;
	}

	/**
	 * Set the maximum number of search results to return.
	 * @param maxSearchResults the number
	 */
	public void setMaxSearchResults(int maxSearchResults) {
		this.maxSearchResults = maxSearchResults;
	}
}
