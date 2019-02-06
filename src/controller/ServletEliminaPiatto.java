/* class: ServletEliminaPiatto
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

import manager.ManagerPiatto;

/**
 * Servlet implementation class ServletEliminaPiatto
 */
@WebServlet("/ServletEliminaPiatto")
public class ServletEliminaPiatto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminaPiatto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

			Integer idPiatto = Integer.parseInt(request.getParameter("idPiatto"));

			try {
				ManagerPiatto piattoManager = new ManagerPiatto();
				piattoManager.eliminaPiatto(idPiatto);
				out.print(true);
				return;
			}catch(Exception e) {
				out.print(false);
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
