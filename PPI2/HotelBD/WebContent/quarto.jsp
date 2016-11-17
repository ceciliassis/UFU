<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.Quarto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - QUARTOS</title>
</head>
<body>
<%
	String uid = (String) session.getAttribute("uid");
%>
	<p class="img">
		<img alt="Hotel LIVING" src="resources/imgs/banner.jpg">
	</p>
	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp">Home</a></li>
			<li><a href="HotelServlet?cmd=hotel" class="current">Hoteis</a></li>
			<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar&uid=">Classificações</a></li> <!-- arrumar -->
			</ul>
			<li><a href="ReservaServlet?cmd=reservas">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
	Para reservar no quarto desejado, clique em RESERVAR.<br><br>
		<table cellpadding="10" cellspacing="0">
			<tr>
				<th>Número</th>
				<th>Andar</th>
				<th>Tipo</th>
				<th>Descrição</th>
				<th>Reservar</th>
			</tr>
			<%
				List quartoList = (List) request.getAttribute("quartoList");
				for (Iterator i = quartoList.iterator(); i.hasNext();) {
					Quarto q = (Quarto) i.next();
			%>
			<tr>
				<td><%=q.getNum() %></td>
				<td><%=q.getAndar() %></td>
				<td><%=q.getTipo() %></td>
				<td><%=q.getDescricao() %></td>
				<td><a href="ReservaServlet?cmd=reservar1&qnum=<%=q.getNum()%>&qandar=<%=q.getAndar()%>&id_hotel=<%=q.getId_hotel()%>">reservar</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<p class="autor">desenvolvidos por: Cecília Assis
	<p>
</body>
</html>