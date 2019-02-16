package controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import manager.ManagerPiatto;
import manager.ManagerTavolo;
import model.ProdottiOrdinati;

/**
 * Servlet implementation class ServeltCreaNuovaOrdinazione
 */
@WebServlet("/ServeltAddProdOrdinazione")
public class ServeltAddProdOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServeltAddProdOrdinazione() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();

		ObjectMapper mapper = new ObjectMapper();

		int numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));
		String JsonProducts = request.getParameter("ProdottiOrdinati");
		int ordinazione = Integer.parseInt(request.getParameter("numeroOrdinazione"));
		System.out.println(numeroTavolo);
		try {
			List<ProdottiOrdinati> listaprodotti = mapper.readValue(JsonProducts,new TypeReference<List<ProdottiOrdinati>>(){});
			
			for(ProdottiOrdinati e : listaprodotti)
			{
				ManagerPiatto mp = new  ManagerPiatto();
				boolean exec = mp.InserisciPiattoIntoOrdinazione(e.idPiatto, ordinazione, e.quantita);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
