<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter Amount</title>
</head>
<body>

	<form action="pinDetails" method="POST">
		<input type="hidden" value="${cardno}" name="cardno">
		<input type="hidden" value="${bankName}" name="bankName">
		<table align="center">
			<tr>
				<td><h1>Enter Amount :</h1> </td>
				<td><h1><input type="text" name="amount"></h1></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="ENTER"></td>
			</tr>
		</table>		
	</form>
</body>
</html>