<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc/header_inc.jsp" %>

	<!--<iframe width="100%" height="190" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.co.uk/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=53.467945,-2.233735&amp;aq=&amp;sll=52.8382,-2.327815&amp;sspn=12.797896,36.628418&amp;t=m&amp;ie=UTF8&amp;ll=53.467945,-2.233735&amp;spn=0.00615,0.017885&amp;z=14&amp;output=embed"></iframe>-->
	
	<h2>CPD courses by region</h2>


<script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCKX3mnODZA8Teay3iRZEj-fSr7eo7lzGI&sensor=false&region=GB">
    </script>
    <script type="text/javascript">
    	var geocoder;
    	var map;
      	function initialize() {
      		geocoder = new google.maps.Geocoder();
			var courses = {
				'courses':
						[

							

						/* ML: test data
						{

								'name': 'test course',
								'address': 'Booth East street , manchester',
								'coords' : ''},
								{

								'name': 'test course 2',
								'address': 'Oxford Street, manchester',
								'coords' : ''},
								*/
								

						<c:forEach var="course" items="${courses}" varStatus="status">
						{

/* ML : may be it should be like this?!
	'name': '<c:out value="${course.title}"/>'
*/


						'name': '${course.title}',
						'url' : '${course.url}',
						'address': '${course.street} ${course.town} ${course.postcode}',
                        'coords' : '${course.providerLatitude}, ${course.providerLongitude}'
							}
								<c:if test="${!status.last}">,</c:if>

						
				</c:forEach>
				]

			}
	        map = new google.maps.Map(document.getElementById("map_canvas"),
	            {
	            	center: new google.maps.LatLng(-25.274398, 133.775136),
	            	zoom: 6,
	            	mapTypeId: 'hybrid'
	            });

	       	if (courses) {
	       		for (var level in courses) {
	       			for (var i = 0; i < courses[level].length; i++) {
	       				codeAddress(courses[level][i].address);
					}
	       		}
	       	}

      	}

		function codeAddress(address) {
			geocoder.geocode({'address':address}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					map.setCenter(results[i].geometry.location);
					new google.maps.Marker({
						map: map,
						position: results[i].geometry.location,
						flat: true
					});
				}
			})
		}

     	google.maps.event.addDomListener(window, 'load', initialize);
    </script>

    <div id="map_canvas" style="width:100%; height:250px;"></div>




<%--ML: include index.jsp instead the code below ?!!--%>
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