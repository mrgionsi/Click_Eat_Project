/* class: ServletAggiungiTavolo
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package it.lucaamoriello.clickeat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lucaamoriello.clickeat.manager.ManagerTavolo;

/**
 * Servlet implementation class ServletAggiungiTavolo
 */
@WebServlet("/ServletAggiungiTavolo")
public class ServletAggiungiTavolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAggiungiTavolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		

		String number = request.getParameter("numeroTavolo");
		Integer numeroTavolo = Integer.parseInt(number);

		try {
			ManagerTavolo tavoloManager = new ManagerTavolo();
			tavoloManager.creaTavolo(numeroTavolo);
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
