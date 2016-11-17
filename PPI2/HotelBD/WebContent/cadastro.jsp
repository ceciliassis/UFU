<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/templateLogin.css">
<title>HOTEL LIVINGS - CADASTRO</title>
</head>
<body>
<p><img alt="" src="resources/imgs/banner.jpg"></p>

	<div id="base">
		
		<div class="centralizado2">
		<div class="centralizado4"><%
				String msg = (String) session.getAttribute("cadastro-aviso");
			
				if (msg != null)
					out.println(msg);
			%></div><br><br>
			<form class="campo" action="UsuarioServlet?cmd=cadastrar" method="post">
				Nome <br><input type="text" name="nome" /><br>
				Email <br><input type="text" name="email" /><br>
				CPF <br><input type="text" name="cpf" maxlength="11" size="11"/><br>
				Telefone<br><input type="text" name="ddd" maxlength="2" size="2"><input type="text" name="tel" maxlength="9" size="9"><br>
				Login <br><input type="text" name="login" /><br>
				Senha <br><input type="password" name="senha"/><br><br>
				
				<p align="center"><input class="botao" type="submit" value="Cadastrar" name="btCadastro"/></p><br><br>
				
			
			</form>
		</div>
	</div>
</body>
</html>