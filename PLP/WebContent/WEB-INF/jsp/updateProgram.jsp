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
<form action=""></form>
<table align="center">
		<tr><td>Program Id</td><td>${prog.scheduledProgrammeId}</td></tr>
			<tr><td>Program Name</td><td>${prog.programName}</td></tr>
			<tr><td>Location</td><td>${prog.location}</td></tr>
			<tr><td>Start Date</td><td>${prog.startDate}</td></tr>
			<tr><td>End date</td><td>${prog.endDate}</td></tr>
			<tr><td>Session Per week</td><td>${prog.sessionsPerWeek}</td></tr>
			<tr><td colspan="2"></td></tr>
		</table>
		<form:form action="update.htm"
				method="post" modelAttribute="ProgramsScheduled">

				<tr>
					<td><form:input path="scheduledProgrammeId"
							value="${prog.scheduledProgrammeId}" />
						${prog.scheduledProgrammeId}</td>
					<td><form:input path="programName"
							value="${prog.programName}" /><a
						href="programDetails.htm?pName=${prog.programName}&pId=${prog.scheduledProgrammeId}">${prog.programName}</a></td>
					<td><form:input path="location" value="${prog.location}" />${prog.location}</td>
					<td><form:input path="startDate" value="${prog.startDate}" />${prog.startDate}</td>
					<td><form:input path="endDate" value="${prog.endDate}" />${prog.endDate}</td>
					<td><form:input path="sessionsPerWeek"
							value="${prog.sessionsPerWeek}" />${prog.sessionsPerWeek}</td>
					<td><input type="submit" value="Apply"></td>
				</tr>
			</form:form>
</body>
</html>