<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="records" type="java.sql.ResultSet" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All records</title>
</head>
<body>
<table>
	<tr>
		<th>No.</th>
		<th>Code</th>
	</tr>
	<%
	while (records.next()){
	%>
	<tr>
		<td><%=records.getInt("id") %></td>
		<td><%=records.getString("code") %>
	</tr>
	<%} %>

</table>
<form action="index.html">
		<input type="submit" value="Go Back"/>
	</form>
</body>
</html>