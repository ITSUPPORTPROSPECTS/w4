<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%pageContext.setAttribute("newLineChar", "\n"); %>
<%
String[] alpha= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
pageContext.setAttribute("alpha", alpha);
%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="selectedPage" value="Map" />

<%@include file="inc/header_inc.jsp" %>

	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCKX3mnODZA8Teay3iRZEj-fSr7eo7lzGI&sensor=false&region=GB"></script>
    <script type="text/javascript">
    	var geocoder;
    	var map;
    	var alpha = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
    	var locations = new Array();
    	var n = 0;

      	function initialize() {
      		geocoder = new google.maps.Geocoder();
			var courses = {
				'courses':
					[
					<c:forEach var="course" items="${courses}" varStatus="status">
					{
						'name': "${fn:replace(course.title, newLineChar, "")}",
						'url' : "${course.url}",
						'address': "${course.street} ${course.town} ${course.postcode}",
                    	'coords' : "${course.providerLatitude}, ${course.providerLongitude}"
					}
					<c:if test="${!status.last}">,</c:if>
					</c:forEach>
					]
				}
	        	map = new google.maps.Map(document.getElementById("map_canvas"),
	            {
	            	center: new google.maps.LatLng(54.367759,-4.244018),
	            	zoom: 4,
	            	mapTypeId: 'hybrid'
	            });

	       	if (courses) {
	       		for (var level in courses) {
	       			for (var i = 0; i < courses[level].length; i++) {
	       				if (n < 26) {
		       				if (courses[level][i].coords == "0.0000000000, 0.0000000000") {
		       					if (courses[level][i].address != "  ") {
		       						codeAddress(courses[level][i].address, index);	
		       					}
		       				} else {
		       					var latLng = courses[level][i].coords.split(",");
		       					new google.maps.Marker({
									map: map,
									position: new google.maps.LatLng(latLng[0], latLng[1].trim()),
									icon: "http://maps.google.com/mapfiles/marker"+alpha[n]+".png",
									flat: true
								});
								n++;
		       				}
	       				}
					}
	       		}
	       	}

      	}

		function codeAddress(address, index) {
			geocoder.geocode({'address':address}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					new google.maps.Marker({
						map: map,
						position: results[i].geometry.location,
						icon: "http://maps.google.com/mapfiles/marker"+alpha[index]+".png",
						flat: true
					});
					n++;
				}
			})
		}

     	google.maps.event.addDomListener(window, 'load', initialize);
    </script>

    <div id="map_canvas" style="width:100%; height:250px;"></div>

	<h2 class="margtop">Training courses</h2>
	
	<ul class="structure_1 map_list compact_list">
		<c:choose>
			<c:when test="${not empty courses}">

				

			    <c:forEach var="course" items="${courses}" varStatus="status">
			        <li class="clearfix">
			            <div class="left">
				            <c:if test="${status.count <= 26}">
				            	<img class="map_marker" src="http://maps.google.com/mapfiles/marker${alpha[status.count-1]}.png" />
				            </c:if>
			                <div class="primary item">
			                    <a href="${course.url}" target="_blank">${course.title}</a>
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
				                	<c:when test="${course.startDate != null}">
				                		<fmt:formatDate pattern="dd/MM/yyyy" value="${course.startDate}"/>
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