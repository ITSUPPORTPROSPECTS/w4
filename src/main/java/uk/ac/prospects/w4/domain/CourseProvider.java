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
