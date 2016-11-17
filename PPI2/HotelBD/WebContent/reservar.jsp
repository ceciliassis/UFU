<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User, java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - HOTEIS</title>
</head>
<body>
	<jsp:useBean id="usuario" class="model.User" />
	<%
		String msg = (String) session.getAttribute("reserva-aviso");
		String uid = (String) session.getAttribute("uid");
		String andar = (String) session.getAttribute("qandar");
		String numero = (String) session.getAttribute("qnum");
		String hotel = (String) session.getAttribute("nome_hotel");
		String hid = (String) session.getAttribute("id_hotel");
	%>
	<p class="img">
		<img alt="" src="resources/imgs/banner.jpg">
	</p>
	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp">Home</a></li>
			<li><a href='HotelServlet?cmd=hotel'>Hoteis</a></li>
			<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar&uid=<%=uid%>">Classificacoes</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas" class="current">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
		Para efetuar sua reserva, clique RESERVAR.<br> <br>
		<%
			if (msg != null)
				out.print("<b>" + msg + "</b>");
		%>
		<br>
		<br>
		<form action="ReservaServlet?cmd=reservar2" method="post"
			class="campo">


			Hotel<br> <input type="text" name="hotel" value="<%=hotel%>"
				readonly><br> Quarto <br> <input type="text"
				name="numero" value="<%=numero%>" readonly /><br> Andar <br>
			<input type="text" name="andar" value="<%=andar%>" readonly /><br>
			De<br> <input type="text" name="dia" maxlength="2" size="2" />/
			<input type="text" name="mes" maxlength="2" size="2" />/ <input
				type="text" name="ano" maxlength="4" size="4" /> <br> Até<br>
			<input type="text" name="dia2" maxlength="2" size="2" />/ <input
				type="text" name="mes2" maxlength="2" size="2" />/ <input
				type="text" name="ano2" maxlength="4" size="4" /> <br> <br>
			<input type="hidden" value="<%=numero%>" name="qnum"> <input
				type="hidden" value="<%=andar%>" name="qandar"> <input
				type="hidden" value="<%=hid%>" name="id_hotel"> <input
				type="hidden" value="<%=uid%>" name="id_hospede"> <input
				class="botao" type="submit" name="botao" value="Reservar">

		</form>




	</div>


</body>
</html>