<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="selectedPage" value="Calendar" />

<%@include file="inc/header_inc.jsp" %>

	<div class="calendar_nav">
		<a href="/w4/calendar.htm?month=${previousCalendar.month}&amp;year=${previousCalendar.year}&amp;day=${previousCalendar.monthLastDay}&amp;${calendarUrl}" title="Events in ${previousCalendar.monthTitle} ${previousCalendar.year}" class="prev_month prev plainlink">&nbsp;<span class="offscreen">Events in ${previousCalendar.monthTitle} ${previousCalendar.year}</span></a>
		<h2>
			<c:out value="${selectedCalendar.monthTitle}"/> ${selectedCalendar.year}
		</h2><a href="/w4/calendar.htm?month=${nextCalendar.month}&amp;year=${nextCalendar.year}&amp;day=${nextCalendar.monthFirstDay}&amp;${calendarUrl}" title="Events in ${nextCalendar.monthTitle} ${nextCalendar.year}" class="next_month next plainlink">&nbsp;<span class="offscreen">Events in ${nextCalendar.monthTitle} ${nextCalendar.year}</span></a>
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
					<c:forEach var="weekday" begin="1" end="7" step="1" varStatus ="status">
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
													<span>
													<c:choose>
														<c:when test="${dates[loop_date]==1}">
															<a href="/w4/calendar.htm?day=${loop_date}&amp;month=${selectedCalendar.month}&amp;year=${selectedCalendar.year}&amp;${calendarUrl}" >${loop_date}</a>
														</c:when>
														<c:otherwise>
															${loop_date}
														</c:otherwise>
													</c:choose>

													</span>
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
											<c:if test="${weekday>6}">
												<c:set var="cssClass" value="${cssClass} weekend"/>
											</c:if>
											<c:if test="${selectedCalendar.monthFirstDay==selectedCalendar.day}">
												<c:set var="cssClass" value="${cssClass} selected_day"/>
											</c:if>
										<td class="${cssClass}">
													<span>
														<c:choose>
														<c:when test="${dates[loop_date]==1}">
															<a href="/w4/calendar.htm?day=${loop_date}&amp;month=${selectedCalendar.month}&amp;year=${selectedCalendar.year}&amp;${calendarUrl}" >${loop_date}</a>
														</c:when>
														<c:otherwise>
															${loop_date}
														</c:otherwise>
													</c:choose>
													</span>
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

		<%--	<tr>
				<td class="past_date">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=5" title="Events on 5 November 2012">5</a>
				</td>
				<td class="past_date">
					<span>6</span>
				</td>
				<td class="past_date">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=7" title="Events on 7 November 2012">7</a>
				</td>
				<td class="past_date">
					<span>8</span>
				</td>
				<td class="past_date">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=9" title="Events on 9 November 2012">9</a>
				</td>
				<td class="weekend past_date">
					<span>10</span>
				</td>
				<td class="weekend past_date">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=11" title="Events on 11 November 2012">11</a>
				</td>
			</tr>
			<tr>
				<td class="past_date">
					<span>12</span>
				</td>
				<td class="past_date">
					<span>13</span>
				</td>
				<td class="past_date">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=14" title="Events on 14 November 2012">14</a>
				</td>
				<td class="past_date">
					<span>15</span>
				</td>
				<td class="past_date">
					<span>16</span>
				</td>
				<td class="weekend past_date">
					<span>17</span>
				</td>
				<td class="weekend past_date">
					<span>18</span>
				</td>
			</tr>
			<tr>
				<td class="today selected_day">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=19" title="Events on 19 November 2012">19</a>
				</td>
				<td class="">
					<span>20</span>
				</td>
				<td class="">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=21" title="Events on 21 November 2012">21</a>
				</td>
				<td class="">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=22" title="Events on 22 November 2012">22</a>
				</td>
				<td class="">
					<span>23</span>
				</td>
				<td class="weekend">
					<span>24</span>
				</td>
				<td class="weekend">
					<span>25</span>
				</td>
			</tr>--%>
			<%--<tr>
				<td class="">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=26" title="Events on 26 November 2012">26</a>
				</td>
				<td class="">
					<span>27</span>
				</td>
				<td class="">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=28" title="Events on 28 November 2012">28</a>
				</td>
				<td class="">
					<a href="/pages/prospects/myprospects_dashboard.htm?_eventId=viewDay&amp;month=11&amp;year=2012&amp;day=29" title="Events on 29 November 2012">29</a>
				</td>
				<td class="">
					<span>30</span>
				</td>
				<td class="next_month weekend"><span>&nbsp;</span></td>
				<td class="next_month weekend"><span>&nbsp;</span></td>
			</tr>--%>
		</tbody>
	</table>


<%@include file="index.jsp"%>

<%@include file="inc/footer_inc.jsp" %>