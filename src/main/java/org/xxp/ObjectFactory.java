
package org.xxp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.xxp package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCourses12_QNAME = new QName("http://xxp.org/", "getCourses12");
    private final static QName _GetCourses12Response_QNAME = new QName("http://xxp.org/", "getCourses12Response");
    private final static QName _GetCourses_QNAME = new QName("http://xxp.org/", "getCourses");
    private final static QName _GetCoursesResponse_QNAME = new QName("http://xxp.org/", "getCoursesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.xxp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCourses12Response }
     * 
     */
    public GetCourses12Response createGetCourses12Response() {
        return new GetCourses12Response();
    }

    /**
     * Create an instance of {@link GetCoursesResponse }
     * 
     */
    public GetCoursesResponse createGetCoursesResponse() {
        return new GetCoursesResponse();
    }

    /**
     * Create an instance of {@link GetCourses }
     * 
     */
    public GetCourses createGetCourses() {
        return new GetCourses();
    }

    /**
     * Create an instance of {@link GetCourses12 }
     * 
     */
    public GetCourses12 createGetCourses12() {
        return new GetCourses12();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCourses12 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xxp.org/", name = "getCourses12")
    public JAXBElement<GetCourses12> createGetCourses12(GetCourses12 value) {
        return new JAXBElement<GetCourses12>(_GetCourses12_QNAME, GetCourses12 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCourses12Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xxp.org/", name = "getCourses12Response")
    public JAXBElement<GetCourses12Response> createGetCourses12Response(GetCourses12Response value) {
        return new JAXBElement<GetCourses12Response>(_GetCourses12Response_QNAME, GetCourses12Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCourses }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xxp.org/", name = "getCourses")
    public JAXBElement<GetCourses> createGetCourses(GetCourses value) {
        return new JAXBElement<GetCourses>(_GetCourses_QNAME, GetCourses.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCoursesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xxp.org/", name = "getCoursesResponse")
    public JAXBElement<GetCoursesResponse> createGetCoursesResponse(GetCoursesResponse value) {
        return new JAXBElement<GetCoursesResponse>(_GetCoursesResponse_QNAME, GetCoursesResponse.class, null, value);
    }

}
