package uk.ac.prospects.w4.repository;

/**
 * @author vasileiosl
 *         Date: 21/11/12
 *         Time: 16:02
 */
public class CourseSearchArgument {

	private String providerTitle;
	private String providerId;
	private String startDate;
	private String courseTitle;

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
}
