<%--
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="selectedPage" value="Settings" />

<%@include file="inc/header_inc.jsp" %>

	<h2>CPD course widget settings: University of Somewhere</h2>

	<form action="#" method="get">
		<div class="margbottom">
			<label class="large" for="keywords">Keywords</label>
			<input class="width100" name="keywords" type="text" />
		</div>
		<div class="margbottom">
			<label class="large" for="location">Location</label>
			<select class="width100" name="location">
				<option>Any</option>
				<option>Birmingham</option>
				<option>London</option>
				<option>Manchester</option>
			</select>
		</div>
		<div class="left width50">
			<div class="margright">
				<label for="fromDate">From&nbsp;date&nbsp;<span>dd/mm/yyyy</span></label>
				<input class="width100" name="fromDate" type="text" />
			</div>
		</div>
		<div class="left width50">
			<div>
				<label for="toDate">To&nbsp;date&nbsp;<span>dd/mm/yyyy</span></label>
				<input class="width100" name="toDate" type="text" />
			</div>
		</div>
		<div class="clear">&nbsp;</div>
		<div class="buttongroup righttext">
			<input type="submit" class="btn" value="Save" />
		</div>
	</form>

<%@include file="inc/footer_inc.jsp" %>