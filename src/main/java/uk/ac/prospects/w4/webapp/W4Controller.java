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
		model.addObject("generalUrl", UrlBuilder.buildGeneralURL(provId,provTitle, keyword, fromStartDate, toStartDate, courseTitle));
				model.addObject("calendarUrl", UrlBuilder.buildCalendarURL(provId,provTitle, keyword));
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

		if (BooleanUtils.isTrue(excludeEmptyStartDates)){
			argument.setExcludeEmptyStartDates(true);
		}

		String jsonResult = this.courseRepository.findAllCourses(argument);
		List<Course> courses = CourseGenerator.generateCoursesFromJsonSearchResult(jsonResult);

		ModelAndView model = new ModelAndView("index");
		model.addObject("courses", courses);
		model.addObject("generalUrl", UrlBuilder.buildGeneralURL(provId,provTitle, keyword, fromStartDate, toStartDate, courseTitle));
		model.addObject("calendarUrl", UrlBuilder.buildCalendarURL(provId,provTitle, keyword));
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
															@RequestParam(value = "day", required=false) Integer day,
															@RequestParam(value = "month", required=false) Integer month,
															@RequestParam(value = "year", required=false) Integer year) throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {


		final String [] MONTHS =
		new String [] {"January", "February", "March", "April", "May",
		"June", "July", "August", "September", "October", "November", "December"};


	if(day==null || month== null || year==null){
		Calendar todaysCalendar = Calendar.getInstance();
		todaysCalendar.setTime(new Date());
		day=todaysCalendar.get(Calendar.DAY_OF_MONTH);
		month=todaysCalendar.get(Calendar.MONTH) + 1;
		year = todaysCalendar.get(Calendar.YEAR);
	}

	Calendar calendar = Calendar.getInstance();
	calendar.set(year,month-1, day);
	Calendar monthCalendar = Calendar.getInstance();
	monthCalendar.set(year,month-1, day);
	monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMinimum(Calendar.DAY_OF_MONTH) );
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	fromStartDate = format.format(monthCalendar.getTime());
	System.out.println("fromStartDate="+ fromStartDate);

	monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	toStartDate = format.format(monthCalendar.getTime());
	System.out.println("toStartDate="+ toStartDate);






/*
	if(fromStartDate ==null || fromStartDate.isEmpty() ){
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(new Date());
		monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMinimum(Calendar.DAY_OF_MONTH) );
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		fromStartDate = format.format(monthCalendar.getTime());
		System.out.println("fromStartDate="+ fromStartDate);

		monthCalendar.set(Calendar.DAY_OF_MONTH, monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		toStartDate = format.format(monthCalendar.getTime());
		System.out.println("toStartDate="+ toStartDate);

		
	}
*/

		ModelAndView model = retrieveCourseProviderAndCourse(provId,fromStartDate, toStartDate, keyword, startDate,courseTitle, provTitle, false);
		model.setViewName("calendar");
		/*Calendar todaysCalendar = Calendar.getInstance();
			todaysCalendar.setTime(new Date());
		*/
		CalendarValues selectedCalendar = new CalendarValues(calendar, 0);

		model.addObject("selectedCalendar", selectedCalendar);
		/*model.addObject("selectedMonthTitle", MONTHS[calendar.get(Calendar.MONTH)]);
		model.addObject("selectedYear", calendar.get(Calendar.YEAR));
		model.addObject("selectedDay", calendar.get(Calendar.DAY_OF_MONTH));
		model.addObject("selectedMonth", calendar.get(Calendar.MONTH)+1);
*/


		Calendar todays = Calendar.getInstance();
		todays.setTime(new Date());

		CalendarValues todaysCalendar = new CalendarValues(todays, 0);
		model.addObject("todaysCalendar", todaysCalendar);

		/*model.addObject("todaysMonth", todaysCalendar.get(Calendar.MONTH) + 1);
		model.addObject("todaysDay", todaysCalendar.get(Calendar.DAY_OF_MONTH));
		model.addObject("todaysYear", todaysCalendar.get(Calendar.YEAR));
*/
		
		//create month calendar
		monthCalendar = Calendar.getInstance();
		monthCalendar.set(year, month - 1, day);
		int daysInMonth = monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		model.addObject("daysInMonth", daysInMonth);

		Calendar firstDayOfTheMonth = DateUtils.truncate(monthCalendar, Calendar.MONTH);

		model.addObject("firstDayOfTheMonth", firstDayOfTheMonth.get(Calendar.DAY_OF_MONTH));
		int firstDayOfTheMonthWeekday = firstDayOfTheMonth.get(Calendar.DAY_OF_WEEK);
		if(firstDayOfTheMonthWeekday==1){
			firstDayOfTheMonthWeekday = 7;
		}else {
			firstDayOfTheMonthWeekday--;
		}
		model.addObject("firstDayOfTheMonthWeekday", firstDayOfTheMonthWeekday);


		List<Course> courses = (List<Course>) model.getModel().get("courses");

		//max days in a month
		int[] dates = new int[32];
		for(Course course : courses){
			Calendar courseCalendar = Calendar.getInstance();
			courseCalendar.setTime(course.getStartDate());
			int dayNumber = courseCalendar.get(Calendar.DAY_OF_MONTH);
			dates[dayNumber] = 1;
		}


		fromStartDate = format.format(calendar.getTime());
		System.out.println("fromStartDateForDay="+ fromStartDate);
		toStartDate = fromStartDate;
		ModelAndView modelForSelectedDay = retrieveCourseProviderAndCourse(provId,fromStartDate, toStartDate, keyword, startDate,courseTitle, provTitle, false);
		List<Course> coursesForSelectedDay = (List<Course>) modelForSelectedDay.getModel().get("courses");

		model.addObject("courses", coursesForSelectedDay);
		model.addObject("dates", dates);

		Calendar previousMonthCalendar = Calendar.getInstance();
		previousMonthCalendar.setTime(calendar.getTime());
		//previousMonthCalendar.add(Calendar.MONTH, -1);

		CalendarValues previousCalendar = new CalendarValues(previousMonthCalendar, -1);
		model.addObject("previousCalendar", previousCalendar);

		/*model.addObject("previousMonth", previousMonthCalendar.get(Calendar.MONTH)+1);
		model.addObject("previousMonthYear", previousMonthCalendar.get(Calendar.YEAR));
		model.addObject("previousMonthLastDay", previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		model.addObject("previousMonthTitle", MONTHS[previousMonthCalendar.get(Calendar.MONTH)]);
*/
		Calendar nextMonthCalendar = Calendar.getInstance();
		nextMonthCalendar.setTime(calendar.getTime());
		//nextMonthCalendar.add(Calendar.MONTH, 1);
		CalendarValues nextCalendar = new CalendarValues(nextMonthCalendar, 1);
		model.addObject("nextCalendar", nextCalendar);

		/*model.addObject("nextMonth", nextMonthCalendar.get(Calendar.MONTH)+1);
		model.addObject("nextMonthYear", nextMonthCalendar.get(Calendar.YEAR));
		model.addObject("nextMonthFirstDay",nextMonthCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		model.addObject("nextMonthTitle", MONTHS[nextMonthCalendar.get(Calendar.MONTH)]);
*/




		return model;
	}

	public class CalendarValues implements Serializable{
		private static final long serialVersionUID = -7723338363691165352L;
		private int month;
		private int year;
		private int day;
		private int monthLastDay;
		private int monthFirstDay;
		private String monthTitle;

		final String [] MONTHS =
		new String [] {"January", "February", "March", "April", "May",
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

		CalendarValues(Calendar calendar, int addMonths){
			calendar.add(Calendar.MONTH, addMonths);
			this.month = calendar.get(Calendar.MONTH)+1;
			this.year = calendar.get(Calendar.YEAR);
			this.day = calendar.get(Calendar.DAY_OF_MONTH);
			this.monthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			this.monthFirstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
			this.monthTitle = MONTHS[calendar.get(Calendar.MONTH)];
		}

		public boolean isAfterToday(){
			Calendar todaysCalendar = Calendar.getInstance();
			todaysCalendar.setTime(new Date());

			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, day);

			return calendar.after(todaysCalendar);

		}
		
	}

}
