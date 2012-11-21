<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>NOT DEFINED</title>
	<style type="text/css" media="all">@import "assets/css/reset.css";</style>
	<style type="text/css" media="all">@import "assets/css/style.css";</style>


	<script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCKX3mnODZA8Teay3iRZEj-fSr7eo7lzGI&sensor=false&region=GB">
    </script>
    <script type="text/javascript">
    	var geocoder;
    	var map;
      	function initialize() {
      		geocoder = new google.maps.Geocoder();
			var courses = {
				'courses': [
					{
						'name': 'Course #1',
						'address': '1 Address, Town'
					},
					{
						'name': 'Course #2',
						'address': '2 Address, Town'
					}
				]
			}
	        map = new google.maps.Map(document.getElementById("map_canvas"),
	            {
	            	center: new google.maps.LatLng(-25.274398, 133.775136),
	            	zoom: 15,
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
					map.setCenter(results[0].geometry.location);
					new google.maps.Marker({
						map: map,
						position: results[0].geometry.location,
						flat: true
					});
				}
			})
		}

     	google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
	<ul class="tabs clearfix">
		<li class="selected"><a href="index.htm">List</a></li>
		<li><a href="calendar.htm">Calendar</a></li>
		<li><a href="map.htm">Map</a></li>
		<li class="settings"><a href="settings.htm"><span class="offscreen">Settings</span>&nbsp;</a></li>
	</ul>
	<div id="w4">
		<div class="inner">