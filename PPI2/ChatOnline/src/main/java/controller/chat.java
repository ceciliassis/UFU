package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.msg;

@WebServlet("/chat")
public class chat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public chat() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession(true);
		String msgm = request.getParameter("msg");
		String uid1 = request.getParameter("uid");
		int uid = Integer.parseInt(uid1);
		msg nova = new msg(msgm,uid);		
		nova.addMensagem();
		ses.setAttribute("uid", uid);
		response.sendRedirect("chat.jsp");
		
		
	}

}
