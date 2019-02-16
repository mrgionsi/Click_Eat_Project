package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import manager.ManagerOrdinazione;
import manager.ManagerTavolo;
import model.ProdottiOrdinati;

/**
 * Servlet implementation class ServletCreaOrdinazione
 */
@WebServlet("/ServeltCreaOrdinazione")
public class ServletCreaOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreaOrdinazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		int numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));

		ManagerTavolo manTavolo = new ManagerTavolo();
		ManagerOrdinazione manOrder = new ManagerOrdinazione();
		if((manTavolo.getTavolo(numeroTavolo).isFlagOccupato()))
		{
			
			out.print(gson.toJson(manOrder.ottieniOrdinazione(numeroTavolo)));
		}else {
			out.print(false);
//			manTavolo.setOccupato(numeroTavolo);
//			int ordinazione = manTavolo.getOrdinazioneDiTavolo(numeroTavolo);
//			
//			out.print(gson.toJson(ordinazione));
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
