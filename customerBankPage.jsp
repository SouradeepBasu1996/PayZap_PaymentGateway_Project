<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Bank Page</title>
</head>
<body>
	<h1 align="center"> Welcome to ${bankName} Bank</h1>
	<br>
	<br>
	<br>
	<h3 align="center"> Click on the "Proceed" button to continue</h3>
	<br>
	<br>
	<form action="analyse" method="POST">
	
		<input type="hidden" value="${cardno}" name="cardno">
		<input type="hidden" value="${name}" name="name">
		<input type="hidden" value="${validity}" name="validity">
		<input type="hidden" value="${cvv}" name="cvv"> 
		<input type="hidden" value="${bankName}" name="bankName">
		<input type="hidden" value="${networkName}" name="networkName">
		<table align="center">
			<tr>
				<td>
					<input type="submit" value="PROCEED">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>