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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Code What? When? Where? Widget</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <span class="brand">What? When? Where? Widget</span>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="span12">
<h1>W4 &mdash; a What When Where Widget for CPD</h1>
<p>
Graduate Prospects have developed a <a href="http://en.wikipedia.org/wiki/Web_widget">widget</a> which uses 
<a href="http://www.xcri.co.uk/what-is-xcri-cap.html">XCRI-CAP course data feeds</a> to display 
courses on a calendar and on a map.

It is designed to be suitable as "related information which may 
be of interest" to sit alongside a page's main content.
</p>

<p>
The widget is simple to prepare and deploy. Simply use our <a href="codeGenerator.htm">Code Generator</a>
to produce an HTML code snippet that you can paste into any web page.
</p>

<ul>
<li>Use the <a href="codeGenerator.htm">Code Generator</a></li>
<li>You can also use the <a href="codeGenerator.htm">Code Generator</a> page to preview what the widget looks like </li>
<li><a href="projectdoc/index.html">Documentation</a></li>
<li><a href="http://prospectssoftware.wordpress.com/category/jisc-xcri-cap-w4-project/">Project blog</a></li>
<li><a href="http://www.jisc.ac.uk/whatwedo/programmes/elearning/coursedata/demonstrators/w4.aspx">JISC project page</a></li>
<li><a href="https://github.com/ITSUPPORTPROSPECTS/w4">github repository</a></li>
</ul>

<p>This project is funded by the <a href="http://www.jisc.ac.uk/whatwedo/programmes/elearning/coursedata/demonstrators.aspx">JISC course data programme</a>.</p>

<p><a href="http://www.prospects.ac.uk"><img src="http://gp.prospects.ac.uk/assets/images/logos/powered_by_prospects_colour.gif" alt="Powered by Prospects"/></a></p>
</div>
</div>
</div>

<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="https://raw.github.com/twitter/bootstrap/master/js/bootstrap-dropdown.js"></script>
<script src="https://raw.github.com/twitter/bootstrap/master/js/bootstrap-scrollspy.js"></script>
</body>
</html>