/* class: ServletGetAllUtenti
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import manager.ManagerUtente;
import model.BeanUtente;

@WebServlet("/ServletGetAllUtenti")
public class ServletGetAllUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletGetAllUtenti() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		System.out.println("-----------------------");
		System.out.println("Inizio metodo: doGet - Servlet: controllerUtente");
		System.out.println("-----------------------");

		ManagerUtente utenteManager = new ManagerUtente();

		ArrayList<BeanUtente> listaUtenti = utenteManager.ottieniUtenti() ;
		Gson gson = new Gson();
		out.print(gson.toJson(listaUtenti));

		System.out.println("-----------------------");
		System.out.println("Fine metodo: doGet - Servlet: ControllerUtente");
		System.out.println("-----------------------");
		return;

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
