package uk.ac.prospects.w4.webapp;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author vasileiosl
 *         Date: 15/11/12
 *         Time: 16:15
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CourseGenerator.class})
public class W4ControllerTest {

	@Test
	public void testAnyPage(){
		W4Controller controller = new W4Controller();

		assertThat((String)controller.anyPage("index").getModel().get("msg"), CoreMatchers.equalTo("Any page"));
	}


	/**
	 * test for {@link W4Controller#retrieveGoogleMapLocations(String, String, String, String, String)}
	 * @throws IOException
	 * @throws SAXException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 * @throws ParserConfigurationException
	 */
	@Test
	public void testRetrieveGoogleMapLocations() throws IOException, SAXException, XPathExpressionException, ParseException, ParserConfigurationException {

		W4Controller controller = new W4Controller();

		CourseRepository rep = Mockito.mock(CourseRepository.class);


		String jsonResult = "jsonResult";
		when(rep.findAllCourses(any(CourseSearchArgument.class))).thenReturn("jsonResult");

		PowerMockito.mockStatic(CourseGenerator.class);

		Course course1 = new Course();
		course1.setTitle("course1");
		Course course2 = new Course();
		course2.setTitle("course2");

		List<Course> courses = new ArrayList<Course>();
		courses.add(course1);
		courses.add(course2);
		when(CourseGenerator.generateCoursesFromJsonSearchResult(jsonResult)).thenReturn(courses);

		controller.setCourseRepository(rep);
		ModelAndView model = controller.retrieveGoogleMapLocations(null, null, null, null, null);
		Mockito.verify(rep).findAllCourses(any(CourseSearchArgument.class));
		PowerMockito.verifyStatic(times(1));
    	CourseGenerator.generateCoursesFromJsonSearchResult(jsonResult);
		Assert.assertEquals("map", model.getViewName());
	}

}
