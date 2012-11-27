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


<c:forEach var="error" items="${errors}">
	<p> ${error.key} = ${error.value}</p>
</c:forEach>


<div class="container">
    <div class="row">
        <div class="span12">

            <form action="codeReview" method="get">
                <div>
                    <label for="keywords">Keywords</label>
                    <input id="keywords" class="width100" name="keywords" type="text" value="${param.keywords}"/>
                </div>
                <div>
                    <label class="large" for="provid">Location</label>

					<select id="provid" name="provid" value="${param.provid}">
                        <option value="">All</option>
                        <option value="d43e55c0-e482-4951-a17d-32db287334c9">Adam Smith College</option>
                        <option value="53787b46-a34a-42cb-bdd7-d6d01409ef0d">University of Lincoln</option>
                        <option value="4621aa18-26f2-49a9-8bc9-4517374c293d">Northampton College</option>
                        <option value="d7526895-5496-4c6f-9d01-f460fe75364c">Northampton College Business Centre
                        </option>
                        <option value="7893f7c8-e52a-42f3-a3ed-abd288fc45c3">Bournemouth and Poole College</option>
                        <option value="97e438b1-3cc0-4960-ae83-870bb9a4a260">The Open University</option>
                        <option value="bdb00e68-460f-4881-9c27-75496376ae22">Aston University</option>
                        <option value="a122026e-3859-4993-b8ff-4db9423a9780">University of Cambridge</option>
                        <option value="ea528c0d-5258-443c-b206-23ea6ce23022">Wychwolf.com</option>
                        <option value="09690923-b475-48f2-a67d-704af8498745">Loughborough University</option>
                        <option value="5ddbd38d-9ab5-4a38-95c7-67cd45e8f43f">XXP</option>
                        <option value="00578773-ae00-4b14-9d3a-eac961fae449">Harper Adams University College</option>
                        <option value="5a490930-3981-42d0-9ef1-0d077a30763f">The Courtauld Institute of Art</option>
                        <option value="c7106a89-bb40-4ea7-a039-fbe0daa9dc51">Myerscough College</option>
                        <option value="e98888e1-3959-4bd6-bb0e-3ae1edf8557a">University of Sussex</option>
                    </select>
                </div>
                <div>
                    <div>

                        <label for="fromStartDate">From&nbsp;date&nbsp;<span>(yyyy-mm-dd)</span></label>
                        <input id="fromStartDate" class="width100" name="fromStartDate" type="text" value="${param.fromStartDate}"/>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="toStartDate">To&nbsp;date&nbsp;<span>(yyyy-mm-dd)</span></label>
                        <input id="toStartDate" class="width100" name="toStartDate" type="text" value="${param.toStartDate}"/>
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