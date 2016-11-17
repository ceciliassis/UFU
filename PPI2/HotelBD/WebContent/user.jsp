<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/styles.css">

<%
	String uid = (String) session.getAttribute("uid");
%>
<title>HOTEL LIVING - <%=session.getAttribute("user")%></title>
</head>
<body>
	<jsp:useBean id="reserva" class="model.Reserva"></jsp:useBean>

	<p class="img">
		<img alt="Hotel LIVING" src="resources/imgs/banner.jpg">
	</p>

	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp" class="current">Home</a></li>
			<li><a href="HotelServlet?cmd=hotel">Hoteis</a></li>
			<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar&uid=<%=uid%>">Classificações</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
		Olá, <b><%=session.getAttribute("user")%></b>, seja bem-vindo !<br>
		<br>


		<%
			
			int id = Integer.parseInt(uid);
			int count = reserva.contarReserva(id);

			if (count == -1 || count == 0)
				out.print(
						"Você não tem nenhuma reserva nos hoteis da companhia, para fazer clique em RESERVAS no menu.");

			else
				out.print("Você tem " + count
						+ " reservas nos hoteis da companhia, para fazer uma nova reserva clique em RESERVAS no menu.");
		%>

		<br> <br> Para informações sobre os hoteis da companhia
		LIVING, clique em HOTEIS no menu. <br> <br> Para informações
		sobre sua conta, clique em CONTA no menu.<br> <br> Para sair
		do sistema, clique em SAIR no menu.

	</div>
	<p class="autor">desenvolvidos por: Cecília Assis
	<p>
</body>
</html>