/* class: ServletGetAllTavoli
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package it.lucaamoriello.clickeat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lucaamoriello.clickeat.manager.ManagerTavolo;
import it.lucaamoriello.clickeat.model.BeanTavolo;

import com.google.gson.Gson;


@WebServlet("/ServletGetAllTavoli")
public class ServletGetAllTavoli extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	public ServletGetAllTavoli() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ManagerTavolo tavolo = new ManagerTavolo();

		ArrayList<BeanTavolo> listaTavoli = tavolo.ottieniTavoli() ;
		Gson gson = new Gson();
		out.print(gson.toJson(listaTavoli));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
