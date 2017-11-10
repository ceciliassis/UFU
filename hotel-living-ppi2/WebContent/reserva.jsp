<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.Reserva"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String msg = (String) session.getAttribute("reserva-aviso");
%>
<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - HOTEIS</title>
</head>
<body>
	<jsp:useBean id="usuario" class="model.User" />
	<%
		String uid = (String) session.getAttribute("uid");
		int id = Integer.parseInt(uid);
	%>
	<p class="img">
		<img alt="" src="resources/imgs/banner.jpg">
	</p>
	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp">Home</a></li>
			<li><a href="HotelServlet?cmd=hotel">Hoteis</a></li>
						<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar&uid=<%=uid%>">Classificações</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas" class="current">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
	Para cancelar sua reserva, clique em CANCELAR.<br><br>
	Para alterar sua reserva, clique em seu CÓDIGO.<br><br>
		<table cellpadding="10" cellspacing="0">
			<tr>
				<th>Código</th>
				<th>Data</th>
				<th>De</th>
				<th>Até</th>
				<th>Hotel</th>
				<th>Quarto</th>
				<th>Andar</th>
				<th>Tipo</th>
				<th>Excluir</th>
			</tr>
			<%
				List reservaList = (List) request.getAttribute("reservaList");
				for (Iterator i = reservaList.iterator(); i.hasNext();) {
					Reserva r = (Reserva) i.next();
			%>
			<tr>
				<td><a href="ReservaServlet?cmd=alterar&id=<%=r.getCodigo() %>"><%=r.getCodigo() %></a></td>
				<td><%=r.getData() %></td>
				<td><%=r.getDe() %></td>
				<td><%=r.getAte() %></td>
				<td><%=r.getHotel() %></td>
				<td><%=r.getQuarto() %></td>
				<td><%=r.getAndar() %></td>
				<td><%=r.getTipo() %></td>
				<td><a href="ReservaServlet?cmd=cancelar&id=<%=r.getCodigo() %>">cancelar</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<!--  <a href="ReservaServlet?cmd=listar"> // reservar aqui-->
	</div>
	<p class="autor">desenvolvidos por: Cecília Assis
	<p>
</body>
</html>