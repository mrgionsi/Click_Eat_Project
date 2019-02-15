/* class: ServletGetAllIngredienti
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

import manager.ManagerIngrediente;
import model.BeanIngrediente;


/**
 * Servlet implementation class GenerateListaIngredienti
 */
@WebServlet("/ServletGetAllIngredienti")
public class ServletGetAllIngredienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetAllIngredienti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		
		ManagerIngrediente ingrediente = new ManagerIngrediente();

		ArrayList<BeanIngrediente> listaIngredienti = ingrediente.ottieniListaIngredienti() ;
		 Gson gson = new Gson();

		out.print(gson.toJson(listaIngredienti));

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
