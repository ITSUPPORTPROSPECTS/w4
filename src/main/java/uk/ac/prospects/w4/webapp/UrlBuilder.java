package uk.ac.prospects.w4.webapp;

import org.springframework.util.StringUtils;

/**
 * Constructs the string containing the parameters that need to be preserved accross the widget
 */
public class UrlBuilder {

	public static String buildCalendarURL(String provId, String provTitle, String keyword){
		StringBuilder sb = buildBasicURL(provId, provTitle, keyword);
		return sb.toString();
	}

	public static String buildGeneralURL(String provId, String provTitle, String keyword, String fromStartDate, String toStartDate, String courseTitle){
		StringBuilder sb = buildBasicURL(provId, provTitle, keyword);
		if (StringUtils.hasText(fromStartDate)){
			if (sb.length() > 0)
				sb.append("&");
			sb.append("fromStartDate=" + fromStartDate);
		}
		if (StringUtils.hasText(toStartDate)){
			if (sb.length() > 0)
				sb.append("&");
			sb.append("toStartDate=" + toStartDate);
		}
		if (StringUtils.hasText(courseTitle)){
			if (sb.length() > 0)
				sb.append("&");
			sb.append("courseTitle=" + courseTitle);
		}
		return sb.toString();
	}

	private static StringBuilder buildBasicURL(String provId, String provTitle, String keyword) {
			StringBuilder sb = new StringBuilder();
			if (StringUtils.hasText(provId)){
				sb.append("provid=" + provId);
			}
			if (StringUtils.hasText(provTitle)){
				if (sb.length() > 0)
					sb.append("&");
				sb.append("provTitle=" + provTitle);
			}
			if (StringUtils.hasText(keyword)){
				if (sb.length() > 0)
					sb.append("&");
				sb.append("keyword=" + keyword);
			}
			return sb;
		}
}
