package uk.ac.prospects.w4.repository;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.ac.prospects.w4.domain.Course;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shuaig
 * Date: 20/11/12
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class CourseGenerator {

	private static final String EMPTY_KEY = "\"\":";
	private static final String TEXT_KEY = "\"text\":";
	private static final String TEST_SEARCH_LINK = "http://coursedata.k-int.com:9200/courses/course/_search";
	// xpath for retrieving course information
	private static final String XPATH_COURSE_FOR_JSON_RESULT = "o/hits/hits/e/_source";
	private static final String XPATH_COURSE_ID_FOR_JSON_RESULT = "_id";
	private static final String XPATH_COURSE_DESCRIPTION_FOR_JSON_RESULT = "description";
	private static final String XPATH_COURSE_TITLE_FOT_JSON_RESULT = "title";
	private static final String XPATH_COURSE_URL_FOR_JSON_RESULT = "url";
	// xpath for retrieving course provider information
	private static final String XPATH_COURSE_PROVIDER_ID_FOR_JASON_RESULT = "provid";
	private static final String XPATH_COURSE_PROVIDER_URL_FOR_JASON_RESULT = "provuri";
	private static final String XPATH_COURSE_PROVIDER_LOCATION_LONGITUDE_FOR_JASON_RESULT = "provloc/lon";
	private static final String XPATH_COURSE_PROVIDER_LOCATION_LATITUDE_FOR_JASON_RESULT = "provloc/lat";

	/**
	 * generate courses from json search results
	 *
	 * @param jsonContent course json search result
	 * @return a list of courses object
	 * @throws IOException
	 * @throws SAXException
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 */
	public static List<Course> generateCoursesFromJsonSearchResult(String jsonContent) throws IOException,
			SAXException, XPathExpressionException, ParserConfigurationException {
		List<Course> courses = new ArrayList<Course>();

		if (!StringUtils.hasText(jsonContent)) {
			//retrieve all courses
			jsonContent = retrieveAllCoursesByJson();
		}

		//replace "": characters to "text":
		jsonContent = jsonContent.replace(EMPTY_KEY, TEXT_KEY);
		XMLSerializer serializer = new XMLSerializer();
		serializer.setTypeHintsEnabled(false);
		JSON json = JSONSerializer.toJSON(jsonContent);
		String xml = serializer.write(json);
		NodeList courseNodeList = getCoursesNodeList(xml);
		for (int index = 0; index < courseNodeList.getLength(); index++) {
			Course course = new Course();
			addNodeInformationToCourse(courseNodeList.item(index), course);
			courses.add(course);
		}
		return courses;
	}

	private static String retrieveAllCoursesByJson() throws IOException {
		URL testJasonLink = new URL(TEST_SEARCH_LINK);
		StringBuffer jasonContentBuffer = new StringBuffer("");
		BufferedReader in = new BufferedReader(new InputStreamReader(testJasonLink.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			jasonContentBuffer.append(inputLine);
		in.close();
		return jasonContentBuffer.toString();
	}

	private static NodeList getCoursesNodeList(String xmlContent) throws ParserConfigurationException, IOException,
			SAXException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression ObjectExpr = xpath.compile(XPATH_COURSE_FOR_JSON_RESULT);
		Object result = ObjectExpr.evaluate(doc, XPathConstants.NODESET);
		return (NodeList) result;
	}

	private static void addNodeInformationToCourse(Node node, Course course) throws XPathExpressionException {
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		//add course information to course object
		XPathExpression idExpr = xpath.compile(XPATH_COURSE_ID_FOR_JSON_RESULT);
		course.setId(idExpr.evaluate(node));

		XPathExpression courseDescriptionExpr = xpath.compile(XPATH_COURSE_DESCRIPTION_FOR_JSON_RESULT);
		course.setDescription(courseDescriptionExpr.evaluate(node));

		XPathExpression titleExpr = xpath.compile(XPATH_COURSE_TITLE_FOT_JSON_RESULT);
		course.setTitle(titleExpr.evaluate(node));

		XPathExpression urlExpr = xpath.compile(XPATH_COURSE_URL_FOR_JSON_RESULT);
		course.setUrl(urlExpr.evaluate(node));

		//add course provider information to course object
		XPathExpression providerIdExpr = xpath.compile(XPATH_COURSE_PROVIDER_ID_FOR_JASON_RESULT);
		course.setProviderId(providerIdExpr.evaluate(node));

		XPathExpression providerLonExpr = xpath.compile(XPATH_COURSE_PROVIDER_LOCATION_LONGITUDE_FOR_JASON_RESULT);
		course.setProviderLongitude(providerLonExpr.evaluate(node));

		XPathExpression providerLatExpr = xpath.compile(XPATH_COURSE_PROVIDER_LOCATION_LATITUDE_FOR_JASON_RESULT);
		course.setProviderLatitude(providerLatExpr.evaluate(node));

		XPathExpression providerUrlExpr = xpath.compile(XPATH_COURSE_PROVIDER_URL_FOR_JASON_RESULT);
		course.setProviderUrl(providerUrlExpr.evaluate(node));
	}

}