<%--
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc/header_inc.jsp" %>
<!-- needs discussionn -->
<!--<h2>CPD courses from: course.providerTitle</h2> -->
<ul class="structure_1">
    <c:choose>
        <c:when test="${not empty courses}">
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
                            <c:choose>
                                <c:when test="${course.startDate!=null}">
                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${course.startDate}"/>
                                </c:when>
                                <c:otherwise>
                                    <p class="chilled">See course details</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <li class="clearfix">
                <p class="centertext chilled">No results found</p>
            </li>
        </c:otherwise>
    </c:choose>    
</ul>

<%@include file="inc/footer_inc.jsp" %>