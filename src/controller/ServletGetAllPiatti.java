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

import manager.ManagerPiatto;
import model.BeanPiatto;


@WebServlet("/ServletGetAllPiatti")
public class ServletGetAllPiatti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletGetAllPiatti() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();


		System.out.println("-----------------------");
		System.out.println("Inizio metodo: doGet - Servlet: ControllerPiatto");
		System.out.println("-----------------------");

		ManagerPiatto piatto = new ManagerPiatto();

		ArrayList<BeanPiatto> listaPiatti = piatto.ottieniListaPiatti();
		Gson gson = new Gson();
		out.print(gson.toJson(listaPiatti));

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
