<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url value="/css/parks.css" var="parksCSS" />
<link rel="stylesheet" href="${parksCSS}" />



<section>

	<div>
		<h1>National Park Services</h1>
	</div>

	<div style="color: black" >
		<c:forEach items ="${parks}" var="park">
			<div style="display: flex">
				<div style="width: auto; display: inline-block">
					<c:set var = "parkCode" value = "${park.parkCode}"/>
					<c:set var = "parkCodeLowerCase" value = "${fn:toLowerCase(parkCode)}" />
					
					<c:url var = 'parkId' value = '/detail?id=${park.parkCode}'/>
					<a href="${parkId}"><img src="<c:url value="/img/parks/${parkCodeLowerCase}.jpg"/>"/></a>
				</div>
				<div style="display: inline-block; text-align: left; flex: 1; vertical-align: top">
					<p style="font-weight: bold; color: black">${park.parkName}</p>
					<p>${park.state}</p>
					<p>${park.description}</p>
				</div>
			</div>
			<br>
		</c:forEach>
	
	</div>	
</section>


















</html>