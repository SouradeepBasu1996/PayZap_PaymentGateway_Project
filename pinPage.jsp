<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pin Page</title>
</head>
<body>
	<form action="calculate" method="POST">
		<input type="hidden" value="${amount}" name="amount">
		<input type="hidden" value="${cardno}" name="cardno">
		<input type="hidden" value="${bankName}" name="bankName">
 		<table align="center">
			<tr>
				<td><h1>Enter PIN : </h1></td>
				<td><input type="password" name="pin"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="ENTER">
			</tr>
		</table>
	</form>
</body>
</html>