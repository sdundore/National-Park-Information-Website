<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url value="/css/parks.css" var="parksCSS" />
<link rel="stylesheet" href="${parksCSS}" />



<head>
<title>Survey Confirmation</title>
</head>
<body>
Thank you for submitting your survey.<p>

<table>
<tr>
	<th>Park Name</th>
	<th>Number of Votes</th>
</tr>

<c:forEach items = "${result}" var = "result">
<tr>
<td>${result.parkName}</td>
<td>${result.numPicked}</td>
</tr>

</c:forEach>
</table>
</body>
</html>