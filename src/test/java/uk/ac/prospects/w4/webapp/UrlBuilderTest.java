package uk.ac.prospects.w4.webapp;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class UrlBuilderTest {

	@Test
	public void testBuildCalendarURL(){
		String paramString = UrlBuilder.buildCalendarURL("id", "provider title", "a keyword", "2011-09-01", "2013-01-01", "course title");

		assertThat(paramString.contains("provid=id"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("provTitle=provider title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("keyword=a keyword"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("courseTitle=course title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("preserve=fromStartDate:2011-09-01,toStartDate:2013-01-01")
						|| paramString.contains("preserve=toStartDate:2013-01-01,fromStartDate:2011-09-01"), CoreMatchers.equalTo(true));
	}

	@Test
	public void testBuildGeneralURL(){
		String paramString = UrlBuilder.buildGeneralURL("id", "provider title", "a keyword", "2011-09-01", "2013-01-01", "course title");

		assertThat(paramString.contains("provid=id"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("provTitle=provider title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("keyword=a keyword"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("courseTitle=course title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("fromStartDate=2011-09-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("toStartDate=2013-01-01"), CoreMatchers.equalTo(true));
	}

	@Test
	public void testBuildCalendarURLForCalendarPage(){
		String paramString = UrlBuilder.buildCalendarURLForCalendarPage("id", "provider title", "a keyword",  "course title", "fromStartDate:2011-09-01,toStartDate:2013-01-01");

		assertThat(paramString.contains("provid=id"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("provTitle=provider title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("keyword=a keyword"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("courseTitle=course title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("preserve=fromStartDate:2011-09-01,toStartDate:2013-01-01")
						|| paramString.contains("preserve=toStartDate:2013-01-01,fromStartDate:2011-09-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("fromStartDate=2011-09-01"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("toStartDate=2013-01-01"), CoreMatchers.equalTo(false));
	}

	@Test
	public void testBuildGeneralURLForCalendarPage(){
		String paramString = UrlBuilder.buildGeneralURLForCalendarPage("id", "provider title", "a keyword",  "course title", "fromStartDate:2011-09-01,toStartDate:2013-01-01");

		assertThat(paramString.contains("provid=id"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("provTitle=provider title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("keyword=a keyword"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("courseTitle=course title"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("fromStartDate=2011-09-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("toStartDate=2013-01-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("preserve=fromStartDate:2011-09-01,toStartDate:2013-01-01")
						&& paramString.contains("preserve=toStartDate:2013-01-01,fromStartDate:2011-09-01"), CoreMatchers.equalTo(false));
	}

	@Test
	public void testBuildCalendarURLNullValues(){
		String paramString = UrlBuilder.buildCalendarURL(null, null, null, null, null, null);
		assertThat(paramString, CoreMatchers.equalTo(""));
	}

	@Test
	public void testBuildGeneralURLNullValues(){
		String paramString = UrlBuilder.buildGeneralURL(null, null, null, null, null, null);
		assertThat(paramString, CoreMatchers.equalTo(""));
	}

	@Test
	public void testBuildCalendarURLForCalendarPageNullValues(){
		String paramString = UrlBuilder.buildCalendarURLForCalendarPage(null, null, null, null, null);
		assertThat(paramString, CoreMatchers.equalTo(""));
	}

	@Test
	public void testBuildGeneralURLForCalendarPageNullValues(){
		String paramString = UrlBuilder.buildGeneralURLForCalendarPage(null, null, null, null, null);
		assertThat(paramString, CoreMatchers.equalTo(""));
	}

	@Test
	public void testBuildCalendarURLOnlyStartDates(){
		String paramString = UrlBuilder.buildCalendarURL(null, null, null, "2011-09-01", "2013-01-01", null);

		assertThat(paramString.contains("provid"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("provTitle"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("keyword"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("courseTitle"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("preserve=fromStartDate:2011-09-01,toStartDate:2013-01-01")
						|| paramString.contains("preserve=toStartDate:2013-01-01,fromStartDate:2011-09-01"), CoreMatchers.equalTo(true));
	}

	@Test
	public void testBuildGeneralURLOnlyStartDates(){
		String paramString = UrlBuilder.buildGeneralURL(null, null, null, "2011-09-01", "2013-01-01", null);

		assertThat(paramString.contains("provid"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("provTitle"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("keyword"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("courseTitle"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("fromStartDate=2011-09-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("toStartDate=2013-01-01"), CoreMatchers.equalTo(true));
	}

	@Test
	public void testBuildCalendarURLForCalendarPageOnlyStartDates(){
		String paramString = UrlBuilder.buildCalendarURLForCalendarPage(null, null, null, null, "fromStartDate:2011-09-01,toStartDate:2013-01-01");

		assertThat(paramString.equals("preserve=fromStartDate:2011-09-01,toStartDate:2013-01-01")
						|| paramString.equals("preserve=toStartDate:2013-01-01,fromStartDate:2011-09-01"), CoreMatchers.equalTo(true));
	}

	@Test
	public void testBuildGeneralURLForCalendarPageOnlyStartDates(){
		String paramString = UrlBuilder.buildGeneralURLForCalendarPage(null, null, null, null, "fromStartDate:2011-09-01,toStartDate:2013-01-01");

		assertThat(paramString.contains("provid"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("provTitle"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("keyword"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("courseTitle"), CoreMatchers.equalTo(false));
		assertThat(paramString.contains("fromStartDate=2011-09-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("toStartDate=2013-01-01"), CoreMatchers.equalTo(true));
		assertThat(paramString.contains("preserve=fromStartDate:2011-09-01,toStartDate:2013-01-01")
						&& paramString.contains("preserve=toStartDate:2013-01-01,fromStartDate:2011-09-01"), CoreMatchers.equalTo(false));
	}
}
