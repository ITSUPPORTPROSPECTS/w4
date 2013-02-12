/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/
package uk.ac.prospects.w4.repository;

/**
 * Holds the search criteria that are used for the elastic searches.
 *
 */
public class CourseSearchArgument {

	private String providerTitle;
	private String providerId;
	private String startDate;
	private String courseTitle;
	private String fromStartDate;
	private String toStartDate;
	private String keyword;
	private boolean excludeEmptyStartDates = false;

	private int maxResults;

	public String getProviderTitle() {
		return providerTitle;
	}

	public void setProviderTitle(String providerTitle) {
		this.providerTitle = providerTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public String getFromStartDate() {
		return fromStartDate;
	}

	public void setFromStartDate(String fromStartDate) {
		this.fromStartDate = fromStartDate;
	}

	public String getToStartDate() {
		return toStartDate;
	}

	public void setToStartDate(String toStartDate) {
		this.toStartDate = toStartDate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isExcludeEmptyStartDates() {
		return excludeEmptyStartDates;
	}

	public void setExcludeEmptyStartDates(boolean excludeEmptyStartDates) {
		this.excludeEmptyStartDates = excludeEmptyStartDates;
	}
}
