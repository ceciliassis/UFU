package controller;

import model.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession(true);
		String login = request.getParameter("user");
		String senha = request.getParameter("senha");

		user usuario = new user(login, senha);

		if (usuario.verificaLogin()) {

			sessao.setAttribute("user", usuario.getLogin());
			sessao.setAttribute("uid", usuario.getId());
			response.sendRedirect("chat.jsp");
		}

		else {
			sessao.setAttribute("aviso", "Você não está cadastro!");
			response.sendRedirect("signup.jsp");
		}

	}

}
