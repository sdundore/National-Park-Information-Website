<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url value="/css/parks.css" var="parksCSS" />
<link rel="stylesheet" href="${parksCSS}" />

<html   
>
<head>
<title>National Park Geek</title>
</head>


	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="/img/logo.png" var="logo" />
		<a href="${homePageHref}"> <img src="${logo}"
			alt="National Park Geek logo" height="130" />
		</a>
	

	<div style = "color: white;">
		<c:url value="/home" var="homeUrl" />
		<a style = "button" href="${homeUrl}">Home</a>
		<c:url value="/survey" var="surveyUrl" />
		<a href="${surveyUrl}">Survey</a>
	</div>
	</header>
	<br>
	<br>
	
	