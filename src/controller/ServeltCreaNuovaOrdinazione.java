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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import manager.ManagerTavolo;

/**
 * Servlet implementation class ServeltCreaNuovaOrdinazione
 */
@WebServlet("/ServeltCreaNuovaOrdinazione")
public class ServeltCreaNuovaOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeltCreaNuovaOrdinazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		class ProdottiOrdinati {
			int idPiatto;
			int quantita;
			
		}
		
		int numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));
		String o = request.getParameter("ProdottiOrdinati");
		System.out.println(o);

		// see there https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
		ProdottiOrdinati[] listaprodotti ;
		//Type listType = new TypeToken<List<ProdottiOrdinati>>() {}.getType();

		listaprodotti = gson.fromJson(o, ProdottiOrdinati[].class);
		for(int i = 0; i<listaprodotti.length; i++)
		{
			System.out.println(listaprodotti[i]);
			System.out.println("LUCA VA QUI IN ECCEZIONE");
		}
	
		ManagerTavolo manTavolo = new ManagerTavolo();

		manTavolo.setOccupato(numeroTavolo);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
