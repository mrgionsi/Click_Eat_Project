package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import manager.ManagerTavolo;
import model.BeanTavolo;

/**
 * Servlet implementation class GenerateListaTavoli
 */
@WebServlet("/ControllerTavolo")
public class ControllerTavolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "jsonfiles/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerTavolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String toGet = request.getParameter("operation"); switching operations
		System.out.println("-----------------------");
		System.out.println("Inizio metodo: doGet - Servlet: ControllerTavolo");
		System.out.println("-----------------------");
		
		ManagerTavolo tavolo = new ManagerTavolo();

		ArrayList<BeanTavolo> listaTavoli = tavolo.ottieniTavoli() ;
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		
		File f = new File(savePath +  "listaTavoli.json");
		if(f.exists() && !f.isDirectory()) { 
			System.out.println("Esiste");
			f.delete();
			f.createNewFile();	
		}
		else {
			System.out.println("non esiste");
			System.out.println("Creazione del file qui:" + f.getAbsolutePath());
			f.createNewFile();	
			
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		
		mapper.writeValue(f, listaTavoli);
		

		System.out.println("-----------------------");
		System.out.println("Fine metodo: doGet - Servlet: ControllerTavolo");
		System.out.println("-----------------------");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
