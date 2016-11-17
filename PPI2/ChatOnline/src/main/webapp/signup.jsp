<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIGN UP</title>
</head>
<body>
	<h1>SIGN UP</h1><p align="right"><a href="index.html"><b>login</b></a> | <a href="about.html"><b>about</b></a> | <a href="users.html"><b>users</b></a>
	<br>
	<h3>
	<%
		String msg = (String) session.getAttribute("aviso");
		if (msg!=null)
			out.print(msg);
	%>
	</h3>
	
	<form action="signup" method="post">
				Login:<br> <input type="text" name="login"/> <br>
				Senha:<br> <input type="password" name="senha"/> <br>
				E-mail: <br><input type="text" name="email"/> <br>	
				<input type="submit" value="Cadastrar">		
	</form>
	
</body>
</html>