<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.Hotel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - HOTEIS</title>
</head>
<%
	String uid = (String) session.getAttribute("uid");
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
				<li><a href="ClassServlet?cmd=mostrar&uid=<%=uid%>">Classificacoes</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
		Para ver os quartos do hotel, clique em seu CÓDIGO.<br>
		<br> Para deixar uma mensagem de avaliação sobre o hotel, clique
		em AVALIE.<br>
		<br>
		<table cellpadding="10" cellspacing="0">
			<tr>
				<th>Código</th>
				<th>Nome</th>
				<th>Endereço</th>
				<th>Cidade</th>
				<th>Pais</th>
				<th>Telefone</th>
				<th>Avalie</th>
			</tr>
			<%
				List hoteisList = (List) request.getAttribute("hotelList");
				for (Iterator i = hoteisList.iterator(); i.hasNext();) {
					Hotel h = (Hotel) i.next();
			%>
			<tr>
				<td><a
					href="HotelServlet?cmd=quarto&id_hotel=<%=h.getCodigo()%>"><%=h.getCodigo()%></a></td>
				<td><%=h.getNome()%></td>
				<td><%=h.getEndereco()%></td>
				<td><%=h.getCidade()%></td>
				<td><%=h.getPais()%></td>
				<td><%=h.getDdd()%> <%=h.getTel()%></td>
				<td><a
					href="ClassServlet?cmd=classificar&hid=<%=h.getCodigo()%>&uid=<%=uid%>">avalie</a></td>
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