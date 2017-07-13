<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/aff.css">
<title>CHAT</title>
</head>
<body>
<%
	int uid = (int) session.getAttribute("uid");
%>
	<h1>CHAT</h1>
	<p align="right">
		<a href="about.html"><b>about</b></a> | <a href="users.html"><b>users</b></a>
		| <a href="logout?uid=<%=uid%>"><b>logout</b></a>
	</p>
	<jsp:useBean id="msg" class="model.msg"></jsp:useBean>
	<jsp:useBean id="user" class="model.user" />
	<%
	
		int count = msg.contarMsg();
		for (int i = 1; i <= count; i++) {
	%>
	<%=msg.buscaData(i)%>
	->
	<%=user.buscaMsg(i)%>
	<i>disse: </i>
	<%=msg.buscaMsg(i)%><br>

	<%
		}
	%>

	<form action="chat" method="post">
		<br> <br> <br> Digite a sua mensagem:<br>
		<textarea rows="1" cols="30" name="msg"></textarea>
		<input type="hidden" value ="<%=uid %>" name=uid>
		<input type="submit" value="enviar"> <br> <br> <br>

	</form>
	<p align="right">
		<jsp:useBean id="logados" class="model.user" />
		<b>users mode ON: </b>
		<%=logados.buscarLogados()%>

	</p>
</body>
</html>
