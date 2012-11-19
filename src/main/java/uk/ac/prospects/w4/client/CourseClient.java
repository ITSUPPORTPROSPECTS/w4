package uk.ac.prospects.w4.client;

import org.xml.sax.SAXException;
import org.xxp.Xxp;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created with IntelliJ IDEA.
 * User: shuaig
 * Date: 18/11/12
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class CourseClient {
    public static void main(String[] args) throws SAXException, ParserConfigurationException {
        String result = new Xxp().getXxpPort().getCourses12("X001008-a");
        System.out.println(result);
    }
}
