<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.Hotel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - CLASSIFICAR</title>
</head>
<%
	String uid = (String) session.getAttribute("uid");
	String hid = (String) session.getAttribute("hid");
%>
<body>
	<p class="img">
		<img alt="Hotel LIVING" src="resources/imgs/banner.jpg">
	</p>
	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp">Home</a></li>
			<li><a href="HotelServlet?cmd=hotel" class="current">Hoteis</a></li>
						<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar&uid=<%=uid%>">Classificações</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
		Para deixar uma mensagem avaliativa sobre o hotel, escreva na caixa e clique em CLASSIFICAR.<br><br>

		<form action="ClassServlet?cmd=add" method="post" class="campo">

			Mensagem<br>
			<textarea rows="5" cols="50" name="mensagem"></textarea>

			<input type="hidden" name="hotel" value="<%=hid%>" /> 
			<input type="hidden"  name="hospede" value="<%=uid%>"/>
			<br><br>
			<input type="submit" name="botao" value="Classificar" class="botao">
		</form>


	</div>
	<p class="autor">desenvolvidos por: Cecília Assis
	<p>
</body>
</html>