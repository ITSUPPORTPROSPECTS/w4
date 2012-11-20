package uk.ac.prospects.w4.domain;

import java.util.Date;

/**
 * course
 *
 * @author vasileiosl
 *         Date: 15/11/12
 *         Time: 11:12
 */
public class Course {

	private String title;

	private String city;

	private String postcode;

	private Date startDate;

	private Date endDate;

	private String id;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
