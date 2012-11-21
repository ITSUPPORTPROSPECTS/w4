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

	private String id;

	private String url;

	private String description;

	//provider information
	private String providerId;

	private String providerTitle;

	private String providerLongitude;

	private String providerLatitude;

	private String providerUrl;

	//presentations information
	private Date startDate;

	private Date endDate;

	private String street;

	private String town;

	private String postcode;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProviderTitle() {
		return providerTitle;
	}

	public void setProviderTitle(String providerTitle) {
		this.providerTitle = providerTitle;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderLongitude() {
		return providerLongitude;
	}

	public void setProviderLongitude(String providerLongitude) {
		this.providerLongitude = providerLongitude;
	}

	public String getProviderLatitude() {
		return providerLatitude;
	}

	public void setProviderLatitude(String providerLatitude) {
		this.providerLatitude = providerLatitude;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * generate a copy of course for different presentations
	 *
	 * @param course
	 * @param index
	 */
	public void copyCourse(Course course, Integer index) {
		this.setId(course.getId() + "suffix" + index);
		this.setProviderLatitude(course.getProviderLatitude());
		this.setTitle(course.getTitle());
		this.setDescription(course.getDescription());
		this.setProviderId(course.getProviderId());
		this.setProviderLongitude(course.getProviderLongitude());
		this.setProviderUrl(course.getProviderUrl());
		this.setUrl(course.getUrl());
	}
}
