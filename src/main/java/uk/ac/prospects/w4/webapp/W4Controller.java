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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Controller class that handles the requests for the widget
 */
@Controller
public class W4Controller {

	private static final int MAX_RESULTS = 200;

	private CourseRepository courseRepository;

	private static final String FROM_TO_START_DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

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

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
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


	@RequestMapping(value = "/calendar.htm", method = RequestMethod.GET)
	public ModelAndView retriveCalendar(@RequestParam(value = "provid", required = false) String provId,
															@RequestParam(value = "fromStartDate", required = false) String fromStartDate,
															@RequestParam(value = "toStartDate", required = false) String toStartDate,
															@RequestParam(value = "keyword", required = false) String keyword,
															@RequestParam(value = "startDate", required = false) String startDate,
															@RequestParam(value = "courseTitle", required = false) String courseTitle,
															@RequestParam(value = "provTitle", required = false) String provTitle,
															@RequestParam(value = "preserve", required = false) String preserve,
															@RequestParam(value = "day", required=false) Integer day,
															@RequestParam(value = "month", required=false) Integer month,
															@RequestParam(value = "year", required=false) Integer year) throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {

		Calendar calendar = createSelectedMonthCalendarFromValuesSetOrIfNotSetFromTodaysDate(year, month, day);

		fromStartDate = getFormattedFromStartDateFromCalendarFirstDayOfTheMonth(calendar, FROM_TO_START_DATE_FORMAT);
		toStartDate = getFormattedToStartDateFromCalendarLastDayOfTheMonth(calendar, FROM_TO_START_DATE_FORMAT);

		//search for all the courses for this selected month
		List<Course> courses  = (List<Course>)retrieveCourseProviderAndCourse(provId, fromStartDate, toStartDate, keyword, startDate, courseTitle, provTitle, false).getModel().get("courses");
		CalendarValues selectedCalendar = new CalendarValues(calendar, 0);
		int [] dates = getTheArrayOfDatesThatHaveAtLeastOneCourseForSelectedCalendar(selectedCalendar, courses);


		ModelAndView model = new ModelAndView("calendar");
		model.addObject("selectedCalendar", selectedCalendar);
		buildAndAddCalendarsToModelFromCurrentCalendar(model, calendar);
		model.addObject("dates", dates);


		//retrieve course list for specific selected date
		fromStartDate = getFormattedDateFromCalendar(calendar, FROM_TO_START_DATE_FORMAT );
		toStartDate = fromStartDate;
		List<Course> coursesForSelectedDay = (List<Course>) retrieveCourseProviderAndCourse(provId, fromStartDate, toStartDate, keyword, startDate, courseTitle, provTitle, false).getModel().get("courses");

		// add course list for specific selected date
		model.addObject("courses", coursesForSelectedDay);

		model.addObject("generalUrl", UrlBuilder.buildGeneralURLForCalendarPage(provId, provTitle, keyword, courseTitle, preserve));
		model.addObject("calendarUrl", UrlBuilder.buildCalendarURLForCalendarPage(provId, provTitle, keyword, courseTitle, preserve));
		return model;
	}


	/**
	 * populates the array that holds 0/1 values in a cell that corresponds to the each day of the month
	 * 1 means there is at least one course with this start date , 0 means no courses for this date
	 * @param selectedCalendar {@code CalendarValues} selected calendar
	 * @param courses - list of all the courses for this month
	 * @return array of 1/0 of course availability for the each month day
	 * @author malvinel
	 */
	private int[] getTheArrayOfDatesThatHaveAtLeastOneCourseForSelectedCalendar(CalendarValues selectedCalendar, List<Course> courses){
		int[] dates = new int[selectedCalendar.getMonthLastDay() + 1];
		for (Course course : courses) {
			Calendar courseCalendar = Calendar.getInstance();
			courseCalendar.setTime(course.getStartDate());
			if(courseCalendar.get(Calendar.MONTH)+1 == selectedCalendar.getMonth() && courseCalendar.get(Calendar.YEAR) == selectedCalendar.getYear()){
				int dayNumber = courseCalendar.get(Calendar.DAY_OF_MONTH);
				dates[dayNumber] = 1;
			}
		}
		return dates;
	}

	/**
	 * builds the necessary {@code CalendarValues} objects for next month, previous month and also current/todays month
	 * @param model - model
	 * @param calendar - currently selected calendar month
	 * @author malvinel
	 */
	private void buildAndAddCalendarsToModelFromCurrentCalendar(ModelAndView model, Calendar calendar){
		Calendar todays = Calendar.getInstance();
		todays.setTime(new Date());
		CalendarValues todaysCalendar = new CalendarValues(todays, 0);
		model.addObject("todaysCalendar", todaysCalendar);


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
	}

	/**
	* Gets the formatted date from the calendar gets the current month and the last day of the month and
	 * formats it to the {@code dateFormatToExpect} pattern
	 * @param calendar {@code Calendar}
	 * @param dateFormatToExpect {@code String} - date format pattern
	 * @return last day of the month formatted date
	 * @author malvinel
	 */
	private String getFormattedFromStartDateFromCalendarFirstDayOfTheMonth(Calendar calendar, String dateFormatToExpect){
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(calendar.getTime());
		monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMinimum(Calendar.DAY_OF_MONTH) );
		return getFormattedDateFromCalendar(monthCalendar, dateFormatToExpect);
	}

	/**
	 * Gets the formatted date from the calendar gets the current month and the first day of the month and
	 * formats it to the {@code dateFormatToExpect} pattern
	 * @param calendar {@code Calendar}
	 * @param dateFormatToExpect {@code String} - date format pattern
	 * @return first day of the month formatted date
	 * @author malvinel
	 */
	private String getFormattedToStartDateFromCalendarLastDayOfTheMonth(Calendar calendar, String dateFormatToExpect){
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(calendar.getTime());
		monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getFormattedDateFromCalendar(monthCalendar, dateFormatToExpect);
	}

	/**
	 * Gets the formatted string date from the calendar date according to the {@code dateFormatToExpect} format
	 * @param calendar - {@code Calendar}
	 * @param dateFormatToExpect {@code String} - format pattern
	 * @return Date formatted String
	 * @author malvinel
	 */
	private String getFormattedDateFromCalendar(Calendar calendar, String dateFormatToExpect){
		SimpleDateFormat format = new SimpleDateFormat(dateFormatToExpect);
		return format.format(calendar.getTime());
	}


	/**
	 * Creates the currently selected calendar month from the the request params provided,
	 * if none are specified the current todays month is used
	 * @param year - year
	 * @param month - month [human readable format -> january =1]
	 * @param day - day
	 * @return Calendar object that holds the currently selected calendar month
	 * @author malvinel
	 */
	private Calendar createSelectedMonthCalendarFromValuesSetOrIfNotSetFromTodaysDate(Integer year, Integer month, Integer day){
		if(day==null || month== null || year==null){
			Calendar todaysCalendar = Calendar.getInstance();
			todaysCalendar.setTime(new Date());
			day=todaysCalendar.get(Calendar.DAY_OF_MONTH);
			month=todaysCalendar.get(Calendar.MONTH) + 1;
			year = todaysCalendar.get(Calendar.YEAR);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month-1, day);
		return calendar;
	}


	//TODO ML: any better name?! anyone?
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
