<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/templateLogin.css">
<title>HOTEL LIVING</title>
</head>

<body>
	<div id="base">
		<p>
			<img alt="" src="resources/imgs/banner.jpg">
		</p>
		<div class="centralizado">
			<br> <br>
			<form action="UsuarioServlet?cmd=logar" method="post" class="campo">
				Login <input type="text" name="login" /><br> 
				Senha<input	type="password" name="senha" /> <br> <br> <br>
				<p align="center">	<input type="submit" value="OK" name="btLogin" class="botao" /><br>
				</p>
			</form>
			<p>
				<%
					String msg = (String) session.getAttribute("index-aviso");
					if (msg != null)
						out.print(msg);
				%>
			</p>
		</div>

	</div>
</body>
</html>