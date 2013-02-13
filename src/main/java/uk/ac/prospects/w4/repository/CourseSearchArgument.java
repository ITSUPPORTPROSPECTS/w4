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

	/** Set the search to return courses whose course provider title contains the supplied string.
	 * @param providerTitle the course provider title to search for
	 */
	public void setProviderTitle(String providerTitle) {
		this.providerTitle = providerTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getProviderId() {
		return providerId;
	}

	/** Set the search to return courses which have the supplied course provider id.
	 * @param providerId the course provider id
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	/** Set the search to return courses which have the supplied start date.
	 * @param startDate the start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	/** Set the search to return courses whose course title contains the supplied string.
	 * @param courseTitle the course title to search for
	 */
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public int getMaxResults() {
		return maxResults;
	}

	/** Set maximum number of courses to return.
	 * @param maxResults the maximum number
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public String getFromStartDate() {
		return fromStartDate;
	}

	/** Set the search to return courses which have a start date after the supplied date.
	 * @param fromStartDate the start date
	 */
	public void setFromStartDate(String fromStartDate) {
		this.fromStartDate = fromStartDate;
	}

	public String getToStartDate() {
		return toStartDate;
	}

	/** Set the search to return courses which have a start date before the supplied date.
	 * @param toStartDate the start date
	 */
	public void setToStartDate(String toStartDate) {
		this.toStartDate = toStartDate;
	}

	public String getKeyword() {
		return keyword;
	}

	/** Set the search to return courses which have the supplied keyword in any field.
	 * @param keyword the keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isExcludeEmptyStartDates() {
		return excludeEmptyStartDates;
	}

	/** Set whether to exclude courses which have no start date.
	 * @param excludeEmptyStartDates true means exclude
	 */
	public void setExcludeEmptyStartDates(boolean excludeEmptyStartDates) {
		this.excludeEmptyStartDates = excludeEmptyStartDates;
	}
}
