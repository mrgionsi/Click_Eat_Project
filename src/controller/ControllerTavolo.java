/* servlet Tavolo, si occupa della logica applicativa riguardante il dato Tavolo
 * author: Andrea Cupito
 * ogni operazione possibile verrà indicata con un numero intero (op):
 * ottieni listaTavoli: 1
 * aggiungere un tavolo al sistema: 2
 * 
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import manager.ManagerTavolo;
import model.BeanTavolo;



@WebServlet("/ControllerTavolo")
public class ControllerTavolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "jsonfiles/";
 
    public ControllerTavolo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String toGet = request.getParameter("op");
		
		if(toGet.equalsIgnoreCase("1")){
			
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
		
		if(toGet.equalsIgnoreCase("2")) {
			
			System.out.println("-----------------------");
			System.out.println("Inizio metodo: doGet - Servlet: ControllerTavolo");
			System.out.println("-----------------------");
			
			System.out.println("nuovo Tavolo");
			
			String number = request.getParameter("numeroTavolo");
			Integer numeroTavolo = Integer.parseInt(number);
			
			try {
				ManagerTavolo tavoloManager = new ManagerTavolo();
				tavoloManager.creaTavolo(numeroTavolo);
				System.out.println("tavolo creato");
			}catch(Exception e) {
				request.setAttribute("exception", e);
				RequestDispatcher rq3 = request.getRequestDispatcher("");//jsp da inserire
				rq3.forward(request, response);
			}
			System.out.println("-----------------------");
			System.out.println("Fine metodo: doGet - Servlet: ControllerTavolo");
			System.out.println("-----------------------");
		}
		
		if(toGet.equalsIgnoreCase("3")) {
			
			Integer numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));
			System.out.println("Cancella tavolo #: " + numeroTavolo);

			try {
				ManagerTavolo tavoloManager = new ManagerTavolo();
				if(tavoloManager.eliminaTavolo(numeroTavolo))
					System.out.println("tavolo eliminato");

			}catch(Exception e) {
				request.setAttribute("exception", e);
				RequestDispatcher rq3 = request.getRequestDispatcher("");//jsp da inserire
				rq3.forward(request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
