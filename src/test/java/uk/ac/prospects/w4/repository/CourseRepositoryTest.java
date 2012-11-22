package uk.ac.prospects.w4.repository;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class CourseRepositoryTest {

	@Test
	public void testfindAllCourses(){
		CourseRepository courseRepository = new CourseRepository();
		courseRepository.setIndex("courses");
		courseRepository.setType("course");
		courseRepository.setServer("http://coursedata.k-int.com:9200");

		CourseSearchArgument courseSearchArgument = new CourseSearchArgument();
		courseSearchArgument.setMaxResults(10);

		String jsonResult = courseRepository.findAllCourses(courseSearchArgument);

		assertThat(jsonResult.contains("\"failed\":0"), CoreMatchers.equalTo(true));
		assertThat(jsonResult.contains("\"timed_out\":false"), CoreMatchers.equalTo(true));
	}
}
