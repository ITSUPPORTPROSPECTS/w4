<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc/header_inc.jsp" %>
<!-- needs discussionn -->
<!--<h2>CPD courses from: course.providerTitle</h2> -->
<ul class="structure_1">

    <c:forEach var="course" items="${courses}" varStatus="status">

        <li class="clearfix">
            <div class="left">
                <div class="primary item">
                    <a href="${course.url}">${course.title}</a>
                </div>
                <div class="secondary item">
                        ${course.providerTitle}
                </div>
                <div class="tertiary item">
                        ${course.town}
                </div>
            </div>
            <div class="right">
                <div class="item_heading">
                    Start date
                </div>
                <div class="item">
                        ${course.startDate} dd/mm/yyyy if not set "See course details"
                </div>
            </div>
        </li>

    </c:forEach>
</ul>

<%@include file="inc/footer_inc.jsp" %>