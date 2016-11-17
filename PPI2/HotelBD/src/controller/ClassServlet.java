package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Classi;
import model.Quarto;

@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClassServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if (cmd.equalsIgnoreCase("classificar"))
			redirectClass(request, response);
		else if (cmd.equalsIgnoreCase("excluir"))
			excluirClassificacao(request, response);
		else if (cmd.equalsIgnoreCase("alterar"))
			alterarClassificacao(request, response);
		else if (cmd.equalsIgnoreCase("mostrar"))
			listaClass(request, response);
		else if (cmd.equalsIgnoreCase("add"))
			addClass(request, response);
		else if (cmd.equalsIgnoreCase("altera2"))
			redirectClass2(request, response);

	}

	public void redirectClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String id_hotel = request.getParameter("hid");
		String id_hospede = request.getParameter("uid");

		ses.setAttribute("hid", id_hotel);
		ses.setAttribute("uid", id_hospede);

		rd = request.getRequestDispatcher("/classificar.jsp");
		rd.forward(request, response);

	}

	public void redirectClass2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String cid = request.getParameter("cid");
		ses.setAttribute("cid", cid);

		rd = request.getRequestDispatcher("/alteraClassi.jsp");
		rd.forward(request, response);

	}

	public void addClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String hid = request.getParameter("hotel");
		String uid = request.getParameter("hospede");
		String msg = request.getParameter("mensagem");

		Classi c = new Classi();

		c.fazerClass(hid, uid, msg);

		rd = request.getRequestDispatcher("/ClassServlet?cmd=mostrar&uid=" + uid);
		rd.forward(request, response);

	}

	public void listaClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String uid = request.getParameter("uid");
		int uid2 = Integer.parseInt(uid);

		Classi classiObj = new Classi();
		List classiList = classiObj.mostrarClassis(uid2);

		request.setAttribute("classiList", classiList);
		rd = request.getRequestDispatcher("/classis.jsp");
		rd.forward(request, response);
	}

	public void excluirClassificacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String cid = request.getParameter("cid");
		String uid = request.getParameter("uid");
		Classi c = new Classi();
		c.excluirClass(cid);

		rd = request.getRequestDispatcher("/ClassServlet?cmd=mostrar&uid=" + uid);
		rd.forward(request, response);

	}

	public void alterarClassificacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		String cid = request.getParameter("cid");
		String msg = request.getParameter("msg");
		Classi c = new Classi();
		int uid = c.buscaHospesde(cid);
		
		if (c.alterarClass(cid, msg)) {
			rd = request.getRequestDispatcher("ClassServlet?cmd=mostrar&uid="+uid);
			rd.forward(request, response);
		}else {
			HttpSession ses = request.getSession(true);
			ses.setAttribute("aviso", "Tivemos um problema na alteração");
		}

	}

}
