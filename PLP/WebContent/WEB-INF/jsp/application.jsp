<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application</title>
</head>
<body>
	<h2>Enter your Credential</h2>
	<form:form action="validate.htm" method="post"
		modelAttribute="Application">
		<table border="1">
			<tr>
				<td>Full name:</td>
				<td><form:input path="fullName" /> <form:errors
						path="fullName" /></td>
			</tr>
			<tr>
				<td>dateOfBirth:</td>
				<td><form:input path="dateOfBirth" /> <form:errors
						path="dateOfBirth" /></td>
			</tr>
			<tr>
				<td>highestQualification:</td>
				<td><form:input path="highestQualification" /> <form:errors
						path="highestQualification" /></td>
			</tr>
			<tr>
				<td>marksObtained:</td>
				<td><form:input path="marksObtained" /> <form:errors
						path="marksObtained" /></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><form:input path="email" /> <form:errors path="email" /></td>
			</tr>
			<tr>
				<td>goals:</td>
				<td><form:input path="goals" /> <form:errors path="goals" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit Details"></td>
			</tr>
		</table>
		<form:hidden path="scheduledProgramId" value="${msg}" />
		<!-- msg = scheduledProgramId passed as ModelandView attribute -->
	</form:form>

</body>
</html>