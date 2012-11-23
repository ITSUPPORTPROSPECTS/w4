package uk.ac.prospects.w4.webapp;

import org.apache.commons.lang.BooleanUtils;
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
import java.text.ParseException;
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
	public ModelAndView anyPage(@PathVariable String page) {

		ModelAndView model = new ModelAndView(page);
		model.addObject("msg", "Any page");
		return model;
	}

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView retrieveCourseProviderAndCourse(	@RequestParam(value = "provid", required = false) String provId,
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
}
