<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.Classi"%>
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
			<li><a href="HotelServlet?cmd=hotel" >Hoteis</a></li>
			<ul class="sub">
				<li><a href="ClassServlet?cmd=mostrar&uid=<%=uid%>" class="current">Classificações</a></li>
			</ul>
			<li><a href="ReservaServlet?cmd=reservas">Reservas</a></li>
			<li><a href="account.jsp">Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
	Para alterar a mensagem avaliativa, clique em seu CÓDIGO.<br><br>
	Para exclir a mensagem avaliatiava, clique em EXCLUIR.<br><br>
		<table cellpadding="10" cellspacing="0">
			<tr>
				<th>Código</th>
				<th>Data</th>
				<th>Mensagem</th>
				<th>Excluir</th>
			</tr>
			<%
				List classiList = (List) request.getAttribute("classiList");
				for (Iterator i = classiList.iterator(); i.hasNext();) {
					Classi c = (Classi) i.next();
			%>
			<tr>
				<td><a href="ClassServlet?cmd=altera2&cid=<%=c.getId_classifica()%>"><%=c.getId_classifica()%></a></td>
				<td><%=c.getData()%></td>
				<td><%=c.getMsg()%></td>
				<td><a href="ClassServlet?cmd=excluir&cid=<%=c.getId_classifica()%>&uid=<%=uid%>">excluir</a></td>
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