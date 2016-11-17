package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String botao = request.getParameter("but");
		System.out.println(botao);

		if (cmd == null) {
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else if (cmd.equalsIgnoreCase("cadastrar"))
			cadastrarUsuario(request, response);
		else if (cmd.equalsIgnoreCase("form")) {
			if (botao.equalsIgnoreCase("atualizar"))
				alterarUsuario(request, response);
			else if (botao.equalsIgnoreCase("excluir"))
				excluirUsuario(request, response);
		} else if (cmd.equalsIgnoreCase("logar"))
			logarUsuario(request, response);
		else if (cmd.equalsIgnoreCase("sair"))
			sairUsuario(request, response);

	}

	private void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User usuario;

		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String id = request.getParameter("uid");

		String cpf = request.getParameter("cpf");
		String numTel = request.getParameter("tel");

		switch (verificaFormat(cpf, numTel)) {
		case 1:
			ses.setAttribute("from", "-");
			ses.setAttribute("conta-aviso", "Formato incorreto de CPF!");
			rd = request.getRequestDispatcher("/account.jsp");
			rd.forward(request, response);
			break;
		case 2:
			ses.setAttribute("from", "-");
			ses.setAttribute("conta-aviso", "Formato incorreto de telefone!");
			rd = request.getRequestDispatcher("/account.jsp");
			rd.forward(request, response);
			break;
		case 0:
			ses.setAttribute("conta-aviso", null);
			break;
		}

		String numDDD = request.getParameter("ddd");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		int telefone = Integer.parseInt(numTel);
		short ddd = (short) Integer.parseInt(numDDD);
		String senha = request.getParameter("senha");

		if (senha == null || senha == "")
			usuario = new User(nome, null, null, email, cpf, ddd, telefone);
		else
			usuario = new User(nome, null, senha, email, cpf, ddd, telefone);

		if (!usuario.alterarHospede(id)) {
			ses.setAttribute("from", "-");
			ses.setAttribute("conta-aviso", "Não foi possivel alterar os dados.");
			rd = request.getRequestDispatcher("/account.jsp");
			rd.forward(request, response);
		} else {
			ses.setAttribute("uid", id);
			rd = request.getRequestDispatcher("/account.jsp");
			rd.forward(request, response);
		}

	}

	private void excluirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;
		User usuario = new User();
		String id = request.getParameter("uid");

		int uid = Integer.parseInt(id);

		if (usuario.excluirHospede(uid)) {
			ses.setAttribute("index-aviso", "Conta Excluida com sucesso!");
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {

			ses.setAttribute("index-aviso", "Sistema Fora do Ar");
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

	private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String cpf = request.getParameter("cpf");
		String numTel = request.getParameter("tel");

		switch (verificaFormat(cpf, numTel)) {
		case 1:
			ses.setAttribute("cadastro-aviso", "Formato incorreto de CPF!");
			rd = request.getRequestDispatcher("/cadastro.jsp");
			rd.forward(request, response);
			break;
		case 2:
			ses.setAttribute("cadastro-aviso", "Formato incorreto de telefone!");
			rd = request.getRequestDispatcher("/cadastro.jsp");
			rd.forward(request, response);
			break;
		case 0:
			ses.setAttribute("cadastro-aviso", null);
			break;
		}

		String numDDD = request.getParameter("ddd");
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		int telefone = Integer.parseInt(numTel);
		short ddd = (short) Integer.parseInt(numDDD);

		User usuario = new User(nome, login, senha, email, cpf, ddd, telefone);

		if (usuario.verificaUser() == 3) {
			ses.setAttribute("cadastro-aviso", "O login digitado já existe!");
			rd = request.getRequestDispatcher("/cadastro.jsp");
			rd.forward(request, response);
		} else if (usuario.verificaUser() == 0)
			if (usuario.cadastrarHospede()) {
				ses.setAttribute("index-aviso", "Cadastrado com sucesso!");
				rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			} else {
				ses.setAttribute("index-aviso", "Sistema Fora do Ar");
				rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}

	}

	private int verificaFormat(String tel, String cpf) {
		if (cpf.contains("-") || cpf.contains("."))
			return 1;
		else if (tel.contains("-"))
			return 2;
		else
			return 0;

	}

	private void logarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		RequestDispatcher rd = null;
		User usuario = new User();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		HttpSession ses = request.getSession(true);

		switch (usuario.verificaUser()) {
		case 1: // recepção
			ses.setAttribute("user", usuario.getNome());
			rd = request.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
			break;
		case 2: // login normal
			ses.setAttribute("user", usuario.getNome());
			String uid = String.valueOf(usuario.getId()); // passando o
															// paramentro string
															// para int
			ses.setAttribute("uid", uid);
			rd = request.getRequestDispatcher("/user.jsp");
			rd.forward(request, response);
			break;
		case 3: // login existente
			ses.setAttribute("index-aviso", "Senha inválida");
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			break;
		case 0: // login inexistente
			ses.setAttribute("cadastro-aviso", "Não te achamos no sistema, faça um cadastro!");
			rd = request.getRequestDispatcher("/cadastro.jsp");
			rd.forward(request, response);
			break;
		}
	}

	private void sairUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession(true);
		sessao.invalidate();
		response.sendRedirect("index.jsp");
	}
}
