<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Code generator</title>
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
            <a class="brand" href="#">What? When? Where? Widget &raquo; Code Generator</a>
        </div>
    </div>
</div>



<div class="container">
    <div class="row">
        <div class="span12">

            <c:if test="${not empty errors}">
                <div class="alert alert-error">
                    <c:forEach var="error" items="${errors}">
                        <p> ${error.key} = ${error.value}</p>
                    </c:forEach>
                </div>
            </c:if>
						
						<p>Simply enter your search criteria and click 'Generate'.</p>

            <form action="codeReview" method="get">
                <div>
                    <label for="keywords">Keywords</label>
                    <input id="keywords" class="width100" name="keywords" type="text" value="${param.keywords}"/>
                </div>
                <div>
                    <label class="large" for="provid">Location</label>

                    <select id="provid" name="provid">
                        <c:forEach var="entry" items="${providers}" varStatus="status">
                            <option value="${entry.key}" ${param.provid == entry.key ? 'selected' : ''}>${entry.value}</option>
                        </c:forEach>

                    </select>
                </div>
                <div>
                    <div>

                        <label for="fromStartDate">From&nbsp;date&nbsp;<span>(yyyy-mm-dd)</span></label>
                        <input id="fromStartDate" class="width100" name="fromStartDate" type="text"
                               value="${param.fromStartDate}"/>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="toStartDate">To&nbsp;date&nbsp;<span>(yyyy-mm-dd)</span></label>
                        <input id="toStartDate" class="width100" name="toStartDate" type="text"
                               value="${param.toStartDate}"/>
                    </div>
                </div>
                <div>
                    <input type="submit" class="btn btn-primary" value="Generate"/>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="https://raw.github.com/twitter/bootstrap/master/js/bootstrap-dropdown.js"></script>
<script src="https://raw.github.com/twitter/bootstrap/master/js/bootstrap-scrollspy.js"></script>
</body>
</html>