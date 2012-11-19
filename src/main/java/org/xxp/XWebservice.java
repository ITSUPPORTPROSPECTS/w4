package org.xxp;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.0
 * 2012-11-19T14:37:04.904Z
 * Generated source version: 2.7.0
 */
@WebService(targetNamespace = "http://xxp.org/", name = "xWebservice")
@XmlSeeAlso({ObjectFactory.class})
public interface XWebservice {

    @WebResult(name = "return", targetNamespace = "")
    @Action(input = "http://xxp.org/xWebservice/getCourses12Request", output = "http://xxp.org/xWebservice/getCourses12Response")
    @RequestWrapper(localName = "getCourses12", targetNamespace = "http://xxp.org/", className = "org.xxp.GetCourses12")
    @WebMethod
    @ResponseWrapper(localName = "getCourses12Response", targetNamespace = "http://xxp.org/", className = "org.xxp.GetCourses12Response")
    public java.lang.String getCourses12(
            @WebParam(name = "arg0", targetNamespace = "")
            java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @Action(input = "http://xxp.org/xWebservice/getCoursesRequest", output = "http://xxp.org/xWebservice/getCoursesResponse")
    @RequestWrapper(localName = "getCourses", targetNamespace = "http://xxp.org/", className = "org.xxp.GetCourses")
    @WebMethod
    @ResponseWrapper(localName = "getCoursesResponse", targetNamespace = "http://xxp.org/", className = "org.xxp.GetCoursesResponse")
    public java.lang.String getCourses(
            @WebParam(name = "arg0", targetNamespace = "")
            java.lang.String arg0
    );
}
