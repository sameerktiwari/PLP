<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>University Admission System - Application ${applicant.applicationId}</h2>
	<form:form action="updateStatus.htm" method="post"
				modelAttribute="Application">
	<table border="2">
		<tr>
			<th>Application ID</th>
			<th>Full Name</th>
			<th>Date of Birth</th>
			<th>Highest Qualification</th>
			<th>Marks Obtained</th>
			<th>Email</th>
			<th>Goals</th>
			<th>Scheduled Programme ID</th>
			<th>Status</th>
			<th>Date of Interview</th>
		</tr>
				<tr>
					<td><form:hidden path="applicationId"
							value="${applicant.applicationId}" /> ${applicant.applicationId}</td>
					<td><form:hidden path="fullName" value="${appli.fullName}" />${applicant.fullName}</td>
					<td><form:hidden path="dateOfBirth"
							value="${applicant.dateOfBirth}" />${applicant.dateOfBirth}</td>
					<td><form:hidden path="highestQualification"
							value="${applicant.highestQualification}" />${applicant.highestQualification}</td>
					<td><form:hidden path="marksObtained"
							value="${applicant.marksObtained}" />${applicant.marksObtained}</td>
					<td><form:hidden path="email" value="${applicant.email}" />${applicant.email}</td>
					<td><form:hidden path="goals" value="${applicant.goals}" />${applicant.goals}</td>
					<td><form:hidden path="scheduledProgramId"
							value="${applicant.scheduledProgramId}" />${applicant.scheduledProgramId}</td>
					<td><form:hidden path="status" value="${applicant.status}" />${applicant.status}</td>
					<td><form:hidden path="dateOfInterview"
							value="${applicant.dateOfInterview}" />${applicant.dateOfInterview}</td>
					<td></td>
				</tr>
				</table>
				<input type="button" value="Accept" name="acceptbtn"><input type="button" value="Reject" name="rejectbtn"><input type="button" value="Confirm" name="confirmbtn">
			</form:form>
			
	<%-- <a href="updateStatus.htm?status=accept"><c:param name="app" value="${applicant}"></c:param><input type="button" value="Accept"></a>
	<a href="updateStatus.htm?appId=${applicant.applicationId}&status=reject"><input type="button" value="Reject"></a>
	<a href="updateStatus.htm?appId=${applicant.applicationId}&status=confirm"><input type="button" value="Confirm"></a> --%>
</body>
</html>