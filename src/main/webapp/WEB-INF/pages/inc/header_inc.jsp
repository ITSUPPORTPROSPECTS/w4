<%--
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
--%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>JISC | ${selectedPage}</title>
	<style type="text/css" media="all">@import "assets/css/reset.css";</style>
	<style type="text/css" media="all">@import "assets/css/style.css";</style>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
	<ul class="tabs clearfix">
		<li class="<c:if test="${selectedPage == 'Calendar'}">selected</c:if>"><a href="calendar.htm?${calendarUrl}">Calendar</a></li>
		<li class="<c:if test="${selectedPage == 'Map'}">selected</c:if>"><a href="map.htm?${generalUrl}">Map</a></li>
		<%--<li class="settings"><a href="settings.htm"><span class="offscreen">Settings</span>&nbsp;</a></li>--%>
	</ul>
	<div id="w4">
		<div class="inner">