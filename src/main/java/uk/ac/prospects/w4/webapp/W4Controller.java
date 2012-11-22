package uk.ac.prospects.w4.webapp;

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
 * @author vasileiosl
 *         Date: 15/11/12
 *         Time: 11:12
 */
@Controller
public class W4Controller {

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
															@RequestParam(value = "keyword", required = false) String keyword
	) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException, ParseException {
		CourseSearchArgument argument = new CourseSearchArgument();
		argument.setMaxResults(10);
		argument.setProviderId(provId);
		argument.setFromStartDate(fromStartDate);
		argument.setToStartDate(toStartDate);
		argument.setKeyword(keyword);

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
															@RequestParam(value = "keyword", required = false) String keyword) throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {
		ModelAndView model = retrieveCourseProviderAndCourse(provId, fromStartDate, toStartDate, keyword);
		model.setViewName("map");
		return model;
	}
}
