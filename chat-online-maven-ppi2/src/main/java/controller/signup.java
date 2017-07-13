package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user;

@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public signup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		user usuario = new user();

		if (usuario.verificaLogin(login)) {
			HttpSession sessao = request.getSession(true);
			sessao.setAttribute("aviso", "O login digitado já existe");
			response.sendRedirect("signup.jsp");
		} else {
			if (senha == null || email == null || login == null || login=="" || senha=="" || email=="") {
				HttpSession sessao = request.getSession(true);
				sessao.setAttribute("aviso", "Campos nulos");
				
			} else {

				usuario.setEmail(email);
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setStatus(false);
				usuario.cadastrarUsuario();
				response.sendRedirect("index.html");
			}

		}

	}

}
