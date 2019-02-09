/* class: ServletLogout
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletLogout")
public class ServletLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ServletLogout() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("adminRoles");
		request.getSession().invalidate();

		Cookie[] cookies = request.getCookies();
	    if (cookies != null)
	        for (Cookie cookie : cookies) {
	            
	        	if(cookie.getValue().equals("cookie")) {
	        	
		        	cookie.setValue("");
		            cookie.setPath("/");
		            cookie.setMaxAge(0);
	        	}
	        	if(cookie.getValue().equals("cookierole")) {
		        	
		        	cookie.setValue("");
		            cookie.setPath("/");
		            cookie.setMaxAge(0);
	        	}
	        }
	
		String redirectedPage = "/login.jsp";
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("./parts/errorpage.jsp");
		rq.forward(request, response);
	}

}
