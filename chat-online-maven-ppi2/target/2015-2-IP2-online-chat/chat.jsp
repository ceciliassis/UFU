<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CHAT</title>
</head>
<body>
	<h1>CHAT</h1>
	<p align="right">
		<a href="about.html"><b>about</b></a> | <a href="users.html"><b>users</b></a>
		| <a href="logout"><b>logout</b></a>
	</p>
	<b><a href="userpage.html">angelo123</a></b>
	<i>disse:</i> E ai, galera?
	<br>
	<br>


	<form action="chat" method="post">
		<br>
		<br>
		<br> Digite a sua mensagem:<br>
		<textarea rows="1" cols="30" name="msg"></textarea>
		<input type="submit" value="enviar"> <br>
		<br>
		<br>
		<p align="right">
			Users logados: <b>angelo123</b> | <b>guest2</b> | <b>guest</b>
		</p>
		</form>
</body>
</html>
