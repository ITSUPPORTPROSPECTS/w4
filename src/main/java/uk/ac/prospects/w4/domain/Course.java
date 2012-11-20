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

	private Date startDate;

	private Date endDate;

	private String id;

	private String url;

	private String description;

	private String providerId;

	private String providerTitle;

	private String providerLongitude;

	private String providerLatitude;

	private String providerUrl;

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
}
