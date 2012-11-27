<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="selectedPage" value="Calendar" />

<%@include file="inc/header_inc.jsp" %>

	<div class="calendar_nav">
		<a href="calendar.htm?month=${previousCalendar.month}&amp;year=${previousCalendar.year}&amp;day=${previousCalendar.monthLastDay}&amp;${calendarUrl}" title="Events in ${previousCalendar.monthTitle} ${previousCalendar.year}" class="prev_month prev plainlink">&nbsp;<span class="offscreen">Events in ${previousCalendar.monthTitle} ${previousCalendar.year}</span></a>
		<h2>
			<c:out value="${selectedCalendar.monthTitle}"/> ${selectedCalendar.year}
		</h2><a href="calendar.htm?month=${nextCalendar.month}&amp;year=${nextCalendar.year}&amp;day=${nextCalendar.monthFirstDay}&amp;${calendarUrl}" title="Events in ${nextCalendar.monthTitle} ${nextCalendar.year}" class="next_month next plainlink">&nbsp;<span class="offscreen">Events in ${nextCalendar.monthTitle} ${nextCalendar.year}</span></a>
	</div>
	<table summary="Contains links to events in ${selectedCalendar.monthTitle} ${selectedCalendar.year}" class="calendar">
		<thead>
			<tr>
				<th>
					Mon<span class="offscreen">day</span>
				</th>
				<th>
					Tue<span class="offscreen">sday</span>
				</th>
				<th>
					Wed<span class="offscreen">nesday</span>
				</th>
				<th>
					Thu<span class="offscreen">rsday</span>
				</th>
				<th>
					Fri<span class="offscreen">day</span>
				</th>
				<th>
					Sat<span class="offscreen">urday</span>
				</th>
				<th>
					Sun<span class="offscreen">day</span>
				</th>
			</tr>
		</thead>
		<tbody>

		<c:set var="loop_date" value="1"/>
		<c:set var="firstDaySet" value="false"/>

		<c:forEach var="week_number" begin="0" end="5" step="1" varStatus ="status">

			<c:if test="${loop_date <= selectedCalendar.monthLastDay}">
			<c:set var="first_week_class" value=""/>
				<c:if test="${week_number==0}">
					<c:set var="first_week_class" value="first_week"/>
				</c:if>


				<tr class="${first_week_class}">
					<c:forEach var="weekday" begin="1" end="7" step="1" varStatus="status">
						<c:choose>
							<c:when test="${firstDaySet}">


								<c:set var="cssClass" value=""/>
								<c:choose>
									<c:when test="${loop_date <= selectedCalendar.monthLastDay}">
											<c:if test="${loop_date==todaysCalendar.day && selectedCalendar.month==todaysCalendar.month && selectedCalendar.year== todaysCalendar.year}">
												<c:set var="cssClass" value="${cssClass} today"/>
											</c:if>
											<c:if test="${selectedCalendar.year < todaysCalendar.year || (selectedCalendar.year == todaysCalendar.year && selectedCalendar.month < todaysCalendar.month)
											||(selectedCalendar.year == todaysCalendar.year && selectedCalendar.month == todaysCalendar.month && loop_date < todaysCalendar.day)}">
												<c:set var="cssClass" value="${cssClass} past_date"/>
											</c:if>
											<c:if test="${weekday>=6}">
												<c:set var="cssClass" value="${cssClass} weekend"/>
											</c:if>
											<c:if test="${loop_date==selectedCalendar.day}">
												<c:set var="cssClass" value="${cssClass} selected_day"/>
											</c:if>
										<td class="${cssClass}">
													<c:choose>
														<c:when test="${dates[loop_date]==1}">
															<a href="calendar.htm?day=${loop_date}&amp;month=${selectedCalendar.month}&amp;year=${selectedCalendar.year}&amp;${calendarUrl}" >${loop_date}</a>
														</c:when>
														<c:otherwise>
															<span>${loop_date}</span>
														</c:otherwise>
													</c:choose>
												</td>
									<c:set var="loop_date" value="${loop_date+1}"/>
									</c:when>
									<c:otherwise>
										<c:set var="cssClass" value="${cssClass} next_month"/>
										<c:if test="${weekend >= 6}">
											<c:set var="cssClass" value="${cssClass} weekend"/>
										</c:if>
										<td class="${cssClass}"><span>&nbsp;</span></td>
									</c:otherwise>
								</c:choose>

							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${selectedCalendar.monthFirstDayWeekday != weekday}">
										<td class="prev_month past_date"><span>&nbsp;</span></td>
									</c:when>
									<c:when test="${selectedCalendar.monthFirstDayWeekday==weekday}">
									<c:set var="cssClass" value=""/>
											<c:if test="${selectedCalendar.monthFirstDay==todaysCalendar.day && selectedCalendar.month==todaysCalendar.month && selectedCalendar.year == todaysCalendar.year}">
												<c:set var="cssClass" value="${cssClass} today"/>
											</c:if>
											<c:if test="${selectedCalendar.year < todaysCalendar.year || (selectedCalendar.year == todaysCalendar.year && selectedCalendar.month < todaysCalendar.month )||
											 (selectedCalendar.year == todaysCalendar.year && selectedCalendar.month == todaysCalendar.month && selectedCalendar.monthFirstDay<todaysCalendar.day)}">
												<c:set var="cssClass" value="${cssClass} past_date"/>
											</c:if>
											<c:if test="${weekday>=6}">
												<c:set var="cssClass" value="${cssClass} weekend"/>
											</c:if>
											<c:if test="${selectedCalendar.monthFirstDay==selectedCalendar.day}">
												<c:set var="cssClass" value="${cssClass} selected_day"/>
											</c:if>
										<td class="${cssClass}">
													<c:choose>
														<c:when test="${dates[loop_date]==1}">
															<a href="calendar.htm?day=${loop_date}&amp;month=${selectedCalendar.month}&amp;year=${selectedCalendar.year}&amp;${calendarUrl}" >${loop_date}</a>
														</c:when>
														<c:otherwise>
															<span>${loop_date}</span>
														</c:otherwise>
													</c:choose>
												</td>
										<c:set var="loop_date" value="${loop_date+1}"/>
										<c:set var="firstDaySet" value="true"/>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</tr>

			</c:if>
			</c:forEach>
		</tbody>
	</table>

<h2 class="margtop">CPD courses on ${selectedCalendar.day}/${selectedCalendar.month}/${selectedCalendar.year}</h2>

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