/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/

package uk.ac.prospects.w4.webapp;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import uk.ac.prospects.w4.domain.Course;
import uk.ac.prospects.w4.repository.CourseRepository;
import uk.ac.prospects.w4.repository.CourseSearchArgument;
import uk.ac.prospects.w4.services.helper.CourseGenerator;
import uk.ac.prospects.w4.webapp.validation.DateValidator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller class that handles the requests for the widget.
 */
@Controller
public class W4Controller {
	private static final String PROVID_PROPERTIES = "provider.properties";

	private static final int MAX_RESULTS = 200;

	private CourseRepository courseRepository;

	private static final String FROM_TO_START_DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	/** 
	 * Display any page, making URLs for the widget available in the model. The supplied
	 * parameters are incorporated into the widget URLs.
	 *
	 * @return the MVC model and view
	 */
	@RequestMapping(value = "/{page}.htm", method = RequestMethod.GET)
	public ModelAndView anyPage(@PathVariable String page,
								@RequestParam(value = "provid", required = false) String provId,
								@RequestParam(value = "fromStartDate", required = false) String fromStartDate,
								@RequestParam(value = "toStartDate", required = false) String toStartDate,
								@RequestParam(value = "keyword", required = false) String keyword,
								@RequestParam(value = "startDate", required = false) String startDate,
								@RequestParam(value = "courseTitle", required = false) String courseTitle,
								@RequestParam(value = "provTitle", required = false) String provTitle) {

		ModelAndView model = new ModelAndView(page);
		model.addObject("generalUrl", UrlBuilder.buildGeneralURL(provId, provTitle, keyword, fromStartDate, toStartDate, courseTitle));
		model.addObject("calendarUrl", UrlBuilder.buildCalendarURL(provId, provTitle, keyword, fromStartDate, toStartDate, courseTitle));
		model.addObject("msg", "Any page");
		return model;
	}

	/**
	 * Adds a list of courses to the model. The supplied parameters are used in the search for courses.
	 */
	public ModelAndView retrieveCourseProviderAndCourse(@RequestParam(value = "provid", required = false) String provId,
														@RequestParam(value = "fromStartDate", required = false) String fromStartDate,
														@RequestParam(value = "toStartDate", required = false) String toStartDate,
														@RequestParam(value = "keyword", required = false) String keyword,
														@RequestParam(value = "startDate", required = false) String startDate,
														@RequestParam(value = "courseTitle", required = false) String courseTitle,
														@RequestParam(value = "provTitle", required = false) String provTitle,
														Boolean excludeEmptyStartDates
	) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException, ParseException {
		CourseSearchArgument argument = new CourseSearchArgument();
		argument.setMaxResults(MAX_RESULTS);
		argument.setProviderId(provId);
		argument.setFromStartDate(fromStartDate);
		argument.setToStartDate(toStartDate);
		argument.setKeyword(keyword);
		argument.setStartDate(startDate);
		argument.setCourseTitle(courseTitle);
		argument.setProviderTitle(provTitle);

		if (BooleanUtils.isTrue(excludeEmptyStartDates)) {
			argument.setExcludeEmptyStartDates(true);
		}

		String jsonResult = this.courseRepository.findAllCourses(argument);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start = startDate != null ? dateFormat.parse(startDate) : null;
		Date startFrom = fromStartDate != null ? dateFormat.parse(fromStartDate) : null;
		Date startTo = toStartDate != null ? dateFormat.parse(toStartDate) : null;
		List<Course> courses = CourseGenerator.generateCoursesFromJsonSearchResult(jsonResult, start, startFrom, startTo);

		ModelAndView model = new ModelAndView("index");
		model.addObject("courses", courses);
		model.addObject("generalUrl", UrlBuilder.buildGeneralURL(provId, provTitle, keyword, fromStartDate, toStartDate, courseTitle));
		model.addObject("calendarUrl", UrlBuilder.buildCalendarURL(provId, provTitle, keyword, fromStartDate, toStartDate, courseTitle));
		return model;
	}

	/**
	 * Show the map in the widget. The supplied parameters are used in the search for courses.
	 */
	@RequestMapping(value = "/map.htm", method = RequestMethod.GET)
	public ModelAndView retrieveGoogleMapLocations(@RequestParam(value = "provid", required = false) String provId,
												   @RequestParam(value = "fromStartDate", required = false) String fromStartDate,
												   @RequestParam(value = "toStartDate", required = false) String toStartDate,
												   @RequestParam(value = "keyword", required = false) String keyword,
												   @RequestParam(value = "startDate", required = false) String startDate,
												   @RequestParam(value = "courseTitle", required = false) String courseTitle,
												   @RequestParam(value = "provTitle", required = false) String provTitle) throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {
		ModelAndView model = retrieveCourseProviderAndCourse(provId, fromStartDate, toStartDate, keyword, startDate, courseTitle, provTitle, false);
		model.setViewName("map");
		return model;
	}


	/**
	 * Show the calendar in the widget. The supplied parameters are used in the search for courses.
	 */
	@RequestMapping(value = "/calendar.htm", method = RequestMethod.GET)
	public ModelAndView retriveCalendar(@RequestParam(value = "provid", required = false) String provId,
										@RequestParam(value = "fromStartDate", required = false) String fromStartDate,
										@RequestParam(value = "toStartDate", required = false) String toStartDate,
										@RequestParam(value = "keyword", required = false) String keyword,
										@RequestParam(value = "startDate", required = false) String startDate,
										@RequestParam(value = "courseTitle", required = false) String courseTitle,
										@RequestParam(value = "provTitle", required = false) String provTitle,
										@RequestParam(value = "preserve", required = false) String preserve,
										@RequestParam(value = "day", required = false) Integer day,
										@RequestParam(value = "month", required = false) Integer month,
										@RequestParam(value = "year", required = false) Integer year) throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {

		Calendar calendar = createSelectedMonthCalendarFromValuesSetOrIfNotSetFromTodaysDate(year, month, day);

		fromStartDate = getFormattedFromStartDateFromCalendarFirstDayOfTheMonth(calendar, FROM_TO_START_DATE_FORMAT);
		toStartDate = getFormattedToStartDateFromCalendarLastDayOfTheMonth(calendar, FROM_TO_START_DATE_FORMAT);

		//search for all the courses for this selected month
		ModelAndView model = retrieveCourseProviderAndCourse(provId, fromStartDate, toStartDate, keyword, startDate, courseTitle, provTitle, false);
		model.setViewName("calendar");

		CalendarValues selectedCalendar = new CalendarValues(calendar, 0);
		model.addObject("selectedCalendar", selectedCalendar);

		Calendar todays = Calendar.getInstance();
		todays.setTime(new Date());
		CalendarValues todaysCalendar = new CalendarValues(todays, 0);
		model.addObject("todaysCalendar", todaysCalendar);

		List<Course> courses = (List<Course>) model.getModel().get("courses");

		//construct the array that stores the dates available for calendar
		int[] dates = new int[selectedCalendar.getMonthLastDay() + 1];
		for (Course course : courses) {
			Calendar courseCalendar = Calendar.getInstance();
			courseCalendar.setTime(course.getStartDate());
			if (courseCalendar.get(Calendar.MONTH) + 1 == selectedCalendar.getMonth() && courseCalendar.get(Calendar.YEAR) == selectedCalendar.getYear()) {
				int dayNumber = courseCalendar.get(Calendar.DAY_OF_MONTH);
				dates[dayNumber] = 1;
			}
		}
		model.addObject("dates", dates);


		//retrieve course list for specific selected date
		fromStartDate = getFormattedDateFromCalendar(calendar, FROM_TO_START_DATE_FORMAT);
		toStartDate = fromStartDate;
		ModelAndView modelForSelectedDay = retrieveCourseProviderAndCourse(provId, fromStartDate, toStartDate, keyword, startDate, courseTitle, provTitle, false);
		List<Course> coursesForSelectedDay = (List<Course>) modelForSelectedDay.getModel().get("courses");

		// add course list for specific selected date
		model.addObject("courses", coursesForSelectedDay);

		//previous month calendar
		Calendar previousMonthCalendar = Calendar.getInstance();
		previousMonthCalendar.setTime(calendar.getTime());
		CalendarValues previousCalendar = new CalendarValues(previousMonthCalendar, -1);
		model.addObject("previousCalendar", previousCalendar);

		//next month calendar
		Calendar nextMonthCalendar = Calendar.getInstance();
		nextMonthCalendar.setTime(calendar.getTime());
		CalendarValues nextCalendar = new CalendarValues(nextMonthCalendar, 1);
		model.addObject("nextCalendar", nextCalendar);

		model.addObject("generalUrl", UrlBuilder.buildGeneralURLForCalendarPage(provId, provTitle, keyword, courseTitle, preserve));
		model.addObject("calendarUrl", UrlBuilder.buildCalendarURLForCalendarPage(provId, provTitle, keyword, courseTitle, preserve));
		return model;
	}

	/**
	 * Show the code generator with HTML snippet and preview. The supplied parameters are used in the search for courses.
	 */
	@RequestMapping(value = "/codeReview", method = RequestMethod.GET)
	public ModelAndView generateIframeURL(@RequestParam(value = "provid", required = false) String provId,
										  @RequestParam(value = "fromStartDate", required = false) String fromStartDate,
										  @RequestParam(value = "toStartDate", required = false) String toStartDate,
										  @RequestParam(value = "keywords", required = false) String keywords,
										  @RequestParam(value = "provTitle", required = false) String provTitle,
										  @RequestParam(value = "courseTitle", required = false) String courseTitle
	) throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {

		ModelAndView model = new ModelAndView("preview");

		Map<String, String> errors = new HashMap<String, String>();
		if (!DateValidator.validate(fromStartDate)) {
			errors.put("fromStartDate", "'From date' is entered incorrectly. Please check the format.");
		}

		if (!DateValidator.validate(toStartDate)) {
			errors.put("toStartDate", "'To date' is entered incorrectly. Please check the format.");

		}
		if (errors.isEmpty()) {
			model.addObject("searchParams", UrlBuilder.buildCalendarURL(provId, provTitle, keywords, fromStartDate, toStartDate, courseTitle));
		} else {

			model = new ModelAndView("codeGenerator");
			model.addObject("errors", errors);
		}

		return loadProvider(model);
	}

	/**
	 * Show the code generator. The supplied parameters are used in the search for courses.
	 */
	@RequestMapping(value = "codeGenerator.htm", method = RequestMethod.GET)
	public ModelAndView codeGeneratorSearchPage() {
		ModelAndView model = new ModelAndView("codeGenerator");
		return loadProvider(model);

	}

	private ModelAndView loadProvider(ModelAndView model) {
		final Properties properties = new Properties();
		try {

			properties.load(this.getClass().getResourceAsStream(PROVID_PROPERTIES));
			Map<String, String> providers = new HashMap<String, String>();

			Set<Map.Entry<Object, Object>> providerEntrys = properties.entrySet();
			for (Map.Entry<Object, Object> providerEntry : providerEntrys) {
				providers.put(providerEntry.getKey().toString(), providerEntry.getValue().toString());
			}
			providers.put("", "All");
			model.addObject("providers", providers);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return model;
	}


	private String getFormattedFromStartDateFromCalendarFirstDayOfTheMonth(Calendar calendar, String dateFormatToExpect) {
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(calendar.getTime());
		monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getFormattedDateFromCalendar(monthCalendar, dateFormatToExpect);
	}

	private String getFormattedToStartDateFromCalendarLastDayOfTheMonth(Calendar calendar, String dateFormatToExpect) {
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(calendar.getTime());
		monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getFormattedDateFromCalendar(monthCalendar, dateFormatToExpect);
	}

	private String getFormattedDateFromCalendar(Calendar calendar, String dateFormatToExpect) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormatToExpect);
		return format.format(calendar.getTime());
	}


	private Calendar createSelectedMonthCalendarFromValuesSetOrIfNotSetFromTodaysDate(Integer year, Integer month, Integer day) {
		if (day == null || month == null || year == null) {
			Calendar todaysCalendar = Calendar.getInstance();
			todaysCalendar.setTime(new Date());
			day = todaysCalendar.get(Calendar.DAY_OF_MONTH);
			month = todaysCalendar.get(Calendar.MONTH) + 1;
			year = todaysCalendar.get(Calendar.YEAR);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar;
	}

	/** Represents a month as displayed in a month-to-view calendar. */
	public class CalendarValues implements Serializable {
		private static final long serialVersionUID = -7723338363691165352L;
		private int month;
		private int year;
		private int day;
		private int monthLastDay;
		private int monthFirstDay;
		private int monthFirstDayWeekday;
		private String monthTitle;

		final String[] MONTHS =
				new String[]{"January", "February", "March", "April", "May",
						"June", "July", "August", "September", "October", "November", "December"};

		public int getMonth() {
			return month;
		}

		public int getYear() {
			return year;
		}

		public int getMonthLastDay() {
			return monthLastDay;
		}

		public int getMonthFirstDay() {
			return monthFirstDay;
		}

		public String getMonthTitle() {
			return monthTitle;
		}

		public int getDay() {
			return day;
		}

		public int getMonthFirstDayWeekday() {
			return monthFirstDayWeekday;
		}

		/** Creates a month-to-view calendar based on a given date plus or minus a number of months offset.
		 *
		 * @param calendar the base date
		 * @param the number of months to offset
		 */
		CalendarValues(Calendar calendar, int addMonths) {
			calendar.add(Calendar.MONTH, addMonths);
			this.month = calendar.get(Calendar.MONTH) + 1;
			this.year = calendar.get(Calendar.YEAR);
			this.day = calendar.get(Calendar.DAY_OF_MONTH);
			this.monthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			this.monthFirstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
			this.monthTitle = MONTHS[calendar.get(Calendar.MONTH)];

			Calendar monthCalendar = Calendar.getInstance();
			monthCalendar.setTime(calendar.getTime());
			Calendar firstDayOfTheMonth = DateUtils.truncate(monthCalendar, Calendar.MONTH);
			int firstDayOfTheMonthWeekday = firstDayOfTheMonth.get(Calendar.DAY_OF_WEEK);
			if (firstDayOfTheMonthWeekday == 1) {
				firstDayOfTheMonthWeekday = 7;
			} else {
				firstDayOfTheMonthWeekday--;
			}
			this.monthFirstDayWeekday = firstDayOfTheMonthWeekday;
		}
	}

}
