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

import model.Hotel;
import model.Reserva;

@WebServlet("/ReservaServlet")
public class ReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		if (cmd.equalsIgnoreCase("reservas"))
			listarReservas(request, response);
		else if (cmd.equalsIgnoreCase("reservar1"))
			reservar1(request, response);
		else if (cmd.equalsIgnoreCase("reservar2"))
			reservar2(request, response);
		else if (cmd.equalsIgnoreCase("cancelar"))
			cancelarReserva(request, response);
		else if (cmd.equalsIgnoreCase("alterar"))
			redirectReserva(request, response);
		else if (cmd.equalsIgnoreCase("alterar2"))
			atualizarReserva(request, response);

	}

	private void listarReservas(HttpServletRequest request, HttpServletResponse response) // ok
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		Reserva reservaObj = new Reserva();
		List reservaList = reservaObj.mostrarReserva();
		request.setAttribute("reservaList", reservaList);
		rd = request.getRequestDispatcher("/reserva.jsp");
		rd.forward(request, response);

	}

	private void reservar1(HttpServletRequest request, HttpServletResponse response) // tela
																						// de
																						// reserva
																						// //
																						// de
			throws ServletException, IOException { // ok
		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String qnum = request.getParameter("qnum");
		String qandar = request.getParameter("qandar");
		String id_hotel = request.getParameter("id_hotel");

		ses.setAttribute("qandar", qandar);
		ses.setAttribute("qnum", qnum);
		ses.setAttribute("id_hotel", id_hotel);

		Hotel hotel = new Hotel();
		String nome_hotel = hotel.saberNome(id_hotel);

		ses.setAttribute("nome_hotel", nome_hotel);

		rd = request.getRequestDispatcher("/reservar.jsp");
		rd.forward(request, response);

	}

	private void reservar2(HttpServletRequest request, HttpServletResponse response) // cliente
																						// reserva
			throws ServletException, IOException { // ok
		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String qnum = request.getParameter("qnum");
		String qandar = request.getParameter("qandar");
		String id_hotel = request.getParameter("id_hotel");
		String de = request.getParameter("dia") + "/" + request.getParameter("mes") + "/" + request.getParameter("ano");
		String ate = request.getParameter("dia2") + "/" + request.getParameter("mes2") + "/"
				+ request.getParameter("ano2");

		Reserva reserva = new Reserva();
		if (reserva.verificaDataDe(de, id_hotel, qnum, qandar)) {
			ses.setAttribute("reserva-aviso", "No período de início da sua reserva, já temos outra reserva");
			rd = request.getRequestDispatcher("/reservar.jsp");
			rd.forward(request, response);
		} else if (reserva.verificaDataAte(ate, id_hotel, qnum, qandar)) {
			ses.setAttribute("reserva-aviso", "No período de término da sua reserva, já temos outra reserva");
			rd = request.getRequestDispatcher("/reservar.jsp");
			rd.forward(request, response);
		} else {
			String id_hospede = request.getParameter("id_hospede");
			reserva.cadastrarReserva(de, ate, id_hotel, qnum, qandar, id_hospede);
			rd = request.getRequestDispatcher("ReservaServlet?cmd=reservas");
			rd.forward(request, response);
		}

	}

	private void cancelarReserva(HttpServletRequest request, HttpServletResponse response) // ok
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		Reserva r = new Reserva();

		String id = request.getParameter("id");
		if (r.excluirReserva(id)) {
			rd = request.getRequestDispatcher("ReservaServlet?cmd=reservas");
			rd.forward(request, response);
		}
	}

	private void redirectReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession(true);
		String id = request.getParameter("id");
		RequestDispatcher rd = null;
		ses.setAttribute("id", id);
		rd = request.getRequestDispatcher("alteraReserva.jsp");
		rd.forward(request, response);

	}

	private void atualizarReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession(true);
		RequestDispatcher rd = null;

		String codigo = request.getParameter("codigo");
		String de = request.getParameter("de");
		String ate = request.getParameter("ate");
		String hotel = request.getParameter("hotel");
		String quarto = request.getParameter("quarto");
		String andar = request.getParameter("andar");
	
			
		Reserva r = new Reserva();
		ses.setAttribute("id", codigo);

		if (r.verificaDataDe(de, hotel, quarto, andar)) {
			ses.setAttribute("altera2-aviso", "No período de início da sua reserva, já temos outra reserva");
			rd = request.getRequestDispatcher("/alteraReserva.jsp");
			rd.forward(request, response);
		} else if (r.verificaDataAte(ate, hotel, quarto, andar)) {
			ses.setAttribute("altera2-aviso", "No período de término da sua reserva, já temos outra reserva");
			rd = request.getRequestDispatcher("/alteraReserva.jsp");
			rd.forward(request, response);
		} else {
			if (r.alterarReserva(codigo, de, ate)) {
				rd = request.getRequestDispatcher("ReservaServlet?cmd=reservas");
				rd.forward(request, response);
			} else {
				ses.setAttribute("altera2-aviso", "Tivemos um problema na atualização da(s) data(s)");
				rd = request.getRequestDispatcher("/alteraReserva.jsp");
				rd.forward(request, response);
			}

		}

	}
}
