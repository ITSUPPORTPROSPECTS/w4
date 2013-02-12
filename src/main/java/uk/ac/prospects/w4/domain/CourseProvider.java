/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/
package uk.ac.prospects.w4.domain;

/**
 * Created with IntelliJ IDEA.
 * User: shuaig
 * Date: 19/11/12
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class CourseProvider {
	private String title;
	private String url;
	private String id;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
