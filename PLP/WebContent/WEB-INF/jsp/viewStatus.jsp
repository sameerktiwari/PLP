<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Status</title>
</head>
<body>
	<h2>University Admission System- Applicant</h2>
	<form action="getStatus.htm">
		<table>
			<tr>
				<td>Enter your Application ID:</td>
				<td><input type="text" name="appId"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Get Status"></td>
			</tr>
		</table>
		<c:if test="${applicant ne null}">
			<h3>Your Application status: ${applicant.status}</h3>
			<table>
				<tr>
					<td>Application ID:</td>
					<td>${applicant.applicationId}</td>
				</tr>
				<tr>
					<td>Full Name:</td>
					<td>${applicant.fullName}</td>
				</tr>
				<tr>
					<td>Date of Birth:</td>
					<td>${applicant.dateOfBirth}</td>
				</tr>
				<tr>
					<td>Highest Qualification:</td>
					<td>${applicant.highestQualification}</td>
				</tr>
				<tr>
					<td>Marks Obtained:</td>
					<td>${applicant.marksObtained}</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td>${applicant.email}</td>
				</tr>
				<tr>
					<td>Goals:</td>
					<td>${applicant.goals}</td>
				</tr>
				<tr>
					<td>Scheduled Programmed ID:</td>
					<td>${applicant.scheduledProgramId}</td>
				</tr>
				<tr>
					<td>Status:</td>
					<td>${applicant.status}</td>
				</tr>
				<tr>
					<td>Date of Interview:</td>
					<td>${applicant.dateOfInterview}</td>
				</tr>
			</table>
		</c:if>
	</form>
	<h3>
		<a href="index.jsp">Home</a>
	</h3>
</body>
</html>