<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Program Scheduled</title>
</head>
<body>
	<h2>Programs offered by the University</h2>
	<table border="1">
		<tr>
			<th>Program Name</th>
			<th>Description</th>
			<th>applicantEligibilty</th>
			<th>duration</th>
			<th>degree</th>
		</tr>
		<c:if test="${prog ne null}">
			<tr>
				<td>${prog.programName}</td>
				<td>${prog.description}</td>
				<td>${prog.applicantEligibilty}</td>
				<td>${prog.duration}</td>
				<td>${prog.degree}</td>
				<td>${prog.sessionsPerWeek}</td>
			</tr>
		</c:if>

	</table>
</body>
</html>