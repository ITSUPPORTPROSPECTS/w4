package uk.ac.prospects.w4.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import uk.ac.prospects.w4.domain.Course;
import uk.ac.prospects.w4.repository.CourseGenerator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

/**
 * @author vasileiosl
 *         Date: 15/11/12
 *         Time: 11:12
 */
@Controller
public class W4Controller {

	@RequestMapping(value = "/{page}.htm", method = RequestMethod.GET)
	public ModelAndView anyPage(@PathVariable String page) {

		ModelAndView model = new ModelAndView(page);
		model.addObject("msg", "Any page");
		return model;
	}

	@RequestMapping(value = "/test.htm", method = RequestMethod.GET)
	public void retrieveCourseProviderAndCourse() throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {
		//ModelAndView model = new ModelAndView(page);
		List<Course> courses = CourseGenerator.generateCoursesFromJsonSearchResult("");
		System.out.println(courses.size());
		//return model;
	}

}
