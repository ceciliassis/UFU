<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%


%>
<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - HOTEIS</title>
</head>
<body>
	<jsp:useBean id="reserva" class="model.Reserva" />
	<%
		String rid = (String) session.getAttribute("id");
		reserva.buscarReserva(rid);
	%>
	<p class="img">
		<img alt="" src="resources/imgs/banner.jpg">
	</p>
	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp">Home</a></li>
			<li><a href="HotelServlet?cmd=hotel">Hoteis</a></li>
						<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar">Classificações</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas">Reservas</a></li>
			<li><a href="account.jsp" class="current">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
		Para alterar as datas da sua reserva, clique nos campos e os redigite e clique em
		ALTERAR.<br><br>
		<%
			String msg = (String) session.getAttribute("altera2-aviso");
		%>
		<form action="ReservaServlet?cmd=alterar2" method="post" class="campo">
			
			Código<br>
			<input name="codigo" type="text" value="<%=reserva.getCodigo()%>" readonly/><br>
			De<br>
			<input name="de" type="text" value="<%=reserva.getDe()%>"/><br>
			Até<br>
			<input name="ate" type="text" value="<%=reserva.getAte()%>"/><br>
			Quarto<br>
			<input type="text" name="quarto" value="<%=reserva.getQuarto()%>" readonly/><br>
			Andar <br>
			<input type="text" name="andar" value="<%=reserva.getAndar()%>" readonly/><br> 
			Tipo<br>
			<input type="text" name="tipo" value="<%=reserva.getTipo()%>" readonly/><br> 
			Hotel <br>
			<input type="text" name="hotel1" value="<%=reserva.getHotel()%>" readonly/><br>
			
			<input type="hidden" name =hotel value="<%=reserva.getId_hotel()%>"/>
			<br> <br>

			<input class="botao" type="submit" name="botao" value="Alterar">

		</form>
		
			
		

	</div>


</body>
</html>