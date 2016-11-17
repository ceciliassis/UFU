<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User, java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String msg = (String) session.getAttribute("conta-aviso");
	String from = (String) session.getAttribute("from");

%>
<link rel="stylesheet" href="resources/css/styles.css">
<title>HOTEL LIVING - HOTEIS</title>
</head>
<body>
	<jsp:useBean id="usuario" class="model.User" />
	<%
		String uid = (String) session.getAttribute("uid");
		int id = Integer.parseInt(uid);
		usuario.buscaHospede3(id);
	%>
	<p class="img">
		<img alt="" src="resources/imgs/banner.jpg">
	</p>
	<div class='cssmenu'>
		<ul class="vert-one">
			<li><a href="user.jsp"><%from=null; %>Home</a></li>
			<li><a href="HotelServlet?cmd=hotel"><%from=null; %>Hoteis</a></li>
			<li><a href="ReservaServlet?cmd=reservas"><%from=null; %>Reservas</a></li>
			<li><a href="account.jsp" class="current"><%from=null; %>Conta</a></li>
			<li><a href="UsuarioServlet?cmd=sair">Sair</a></li>
		</ul>
	</div>
	<div class="conteudo">
		Para atualizar seus dados, clique nos campos e os redigite e clique em
		ATUALIZAR.<br>
		<br> Para excluir sua conta, clique em EXCLUIR.<br>
		<br>
		<%
			if(from!=null)
				out.print("<b>"+msg+"</b>");
		%>
		<br>
		<form action="UsuarioServlet?cmd=form" method="post" class="campo">
			
			Nome<br>
			<input name="nome" type="text" value="<%=usuario.getNome()%>"><br>
			CPF<br>
			<input name="cpf" type="text" value="<%=usuario.getCpf()%>" maxlength="11" size="11" readonly><br>
			Telefone<br>
			<input type="text" name="ddd" maxlength="2" size="2"
				value="<%=usuario.getDdd()%>"><input type="text" name="tel"
				maxlength="9" size="9" value="<%=usuario.getTelefone()%>"><br>
			Email <br>
			<input type="text" name="email" value="<%=usuario.getEmail()%>" /><br>
			Login <br>
			<input type="text" name="login" value="<%=usuario.getLogin()%>"	readonly /><br> 
			Senha <br>	<input type="password" name="senha" /><br>
			
			<br> 
			<input type="hidden" value="<%=id%>" name="uid">
			<input class="botao" type="submit" name="but" value="Atualizar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="botao" type="submit" name="but" value="Excluir">

		</form>
		
			
		

	</div>


</body>
</html>