/* class: ServletEliminaTavolo
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ManagerTavolo;

/**
 * Servlet implementation class ServletElminaTavolo
 */
@WebServlet("/ServletEliminaTavolo")
public class ServletEliminaTavolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEliminaTavolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Integer numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));
		System.out.println("Cancella tavolo #: " + numeroTavolo);

		try {
			ManagerTavolo tavoloManager = new ManagerTavolo();
			if(tavoloManager.eliminaTavolo(numeroTavolo))
				System.out.println("tavolo eliminato");
			out.print(true);
			return;
		}catch(Exception e) {
			out.print(false);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
