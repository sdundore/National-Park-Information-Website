<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<c:url value="/css/parks.css" var="parksCSS" />
<link rel="stylesheet" href="${parksCSS}" />
<c:import url="/WEB-INF/jsp/header.jsp" />

<title>Park Details</title>

<h1 style = "font-weight: bold;">${park.parkName} in ${park.state}</h1>


<body>
	<c:set var = "parkCode" value = "${park.parkCode}"/>
	<c:set var = "parkCodeLowerCase" value = "${fn:toLowerCase(parkCode)}" />
	
	<div id = "detail-grid">
		<div id="detail-photo">
			<img src="<c:url value="/img/parks/${parkCodeLowerCase}.jpg"/>"/>
		</div>

	<div id = "detail-details">
	<ul>
		<li>Acreage: <c:out value = "${park.acres}"/> sq miles</li>
		<li>Elevation: <c:out value = "${park.elevation}"/> ft</li>
		<li>Miles of Trail: <c:out value = "${park.trailLength}"/></li>
		<li>Number of Campsite: <c:out value ="${park.numOfCampsites}"/></li>
		<li>Climate: <c:out value = "${park.climate}"/></li>
		<li>Year Founded: <c:out value = "${park.yearFounded}"/></li>
		<li>Annual Visitor Count: <c:out value = "${park.annualVisitors}"/></li>
		<li>Number of Unique Animal Species: <c:out value = "${park.numAnimalSpecies}"/></li>
		<li>Entry Fee: $<c:out value ="${park.entryFee}"/></li>
	</ul>
	</div>	
	</div>
	<div id="detail-description">

	
	<c:out value ="${park.description}"/><br><br>
	"<c:out value = "${park.quote}"/>"<br>
	- <c:out value ="${park.quoteSource}"/>
	</div>
	<p>
	<h1>5 Day Forecast</h1>
		<c:url var="DetailUrl" value="/detail" />
		<form method="POST" action="${detailUrl}" modelAttribute="detail">
	<input type="radio" name="temp" value="Farenheit"/>Farenheit
	<input type="radio" name="temp" value="Celsius"/> Celsius
	<div>
		<input type="submit" value="submit" />
	</div>
	</form>
	
	
	<br>
	<br>
	<div id="forecast-grid">
	<c:forEach items = "${weather}" var = "day">
<div>

		<img id = "forecast-image" src="<c:url value="/img/weather/${day.forecast}.png"/>"/><br>
	<ul id = "forecast-data">
		<c:choose>
		<c:when test = "${temp == 'Celsius'}">
		<li>High: <fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${(day.lowTemp - 32) * (5/9)}"/></li>
		<li>Low: <fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${(day.highTemp - 32) * (5/9)}"/></li>
		</c:when>
		<c:otherwise>
		<li>High: <c:out value = "${day.highTemp}"/></li>
		<li>Low: <c:out value = "${day.lowTemp}"/></li>
		</c:otherwise>
		</c:choose>
		<br>
		
	
		
		<c:if test ="${day.lowTemp < 20}">
			Beware of Frigid Temperatures!<br>
		</c:if>
		<c:if test = "${day.highTemp > 75}">
			Bring an extra gallon of water!<br>
		</c:if>
		<c:if test = "${(day.highTemp - day.lowTemp) > 20}">
			Wear breathable layers!<br>
		</c:if>
		
		<c:choose>
		<c:when test="${day.forecast == 'snow'}">
			Pack Snow Shoes<br>
		</c:when>
		<c:when test = "${day.forecast == 'rain'}">
			Pack Rain Gear and wear Waterproof shoes.<br>
		</c:when>
		<c:when test = "${day.forecast == 'thunderstorms'}">
			Seek shelter and avoid hiking on exposed ridges<br>
		</c:when>
		<c:when test = "${day.forecast == 'sunny'}">
			Pack sun block<br>
		</c:when>
		</c:choose>
	</ul>
	</div>
	</c:forEach>
	</div>
	

</body>
</html>