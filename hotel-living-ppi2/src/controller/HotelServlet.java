package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hotel;
import model.Quarto;

@WebServlet("/HotelServlet")

public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HotelServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		if (cmd.equalsIgnoreCase("hotel"))
			listarHoteis(request, response);
		else if (cmd.equalsIgnoreCase("quarto"))
			listarQuartos(request, response);

	}

	public void listarHoteis(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		Hotel hotelObj = new Hotel();
		List hotelList = hotelObj.mostrarHoteis();
		request.setAttribute("hotelList", hotelList);
		rd = request.getRequestDispatcher("/hotel.jsp");
		rd.forward(request, response);

	}

	public void listarQuartos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;
		String id_hotel = request.getParameter("id_hotel");
		int hid = Integer.parseInt(id_hotel);

		Quarto quartoObj = new Quarto();
		List quartoList = quartoObj.mostrarQuarto(hid);
		request.setAttribute("quartoList", quartoList);
		rd = request.getRequestDispatcher("/quarto.jsp");
		rd.forward(request, response);

	}
}
