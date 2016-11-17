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
	<jsp:useBean id="classi" class="model.Classi" />
	<%
		String cid = (String) session.getAttribute("cid");
		classi.buscarClassi(cid);
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
		Para alterar a mensagem avaliativa, clique na caixa a redigite, depois clique em
		ALTERAR.<br><br>
		<%
			String msg = (String) session.getAttribute("<b>aviso</b>");
		%>
		<form action="ClassServlet?cmd=alterar" method="post" class="campo">
			
			Código<br>
			<input name="cid" type="text" value="<%=classi.getId_classifica() %>" readonly/><br>
			Data<br>
			<input name="data" type="text" value="<%=classi.getData() %>" readonly/><br>
			Mensagem<br>
			<textarea rows="5" cols="30" name="msg" ><%=classi.getMsg()%></textarea>
			<br> <br>

			<input class="botao" type="submit" name="botao" value="Alterar">

		</form>
		
			
		

	</div>


</body>
</html>