<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PayZap</title>
</head>
<body>
	<h1 align="center">You have reached PayZap payment gateway</h1>
	<br>
	<br>
	<br>
	<form action="merchantPage" method="POST">
		<table align="center">
			<tr>
				<td>Card number :</td>
				<td><input type="text" name="cardno"></td>
			</tr>
			<tr>
				<td>Name on Card :</td>
				<td><input type="text" name="nameOnCard"></td>
			</tr>
			<tr>
				<td>Validity :</td>
				<td><input type="text" name="validity"></td>
			</tr>
			<tr>
				<td>CVV :</td>
				<td><input type="password" name="cvv"></td>
			</tr>
			<tr>
				<td>       </td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
			
</body>
</html>