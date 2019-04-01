package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.InvalidInputException;
import model.InputBean;

/**
 * Servlet implementation class CheckInputServlet
 */
//@WebServlet("/ProcessInput")
public class CheckInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	String jdbcUrl = null;
	String dbUsername = null;
	String dbPassword = null;
	
	public void init()throws ServletException{
		jdbcUrl = getServletContext().getInitParameter("jdbcUrl");
		dbUsername = getServletContext().getInitParameter("dbUsername");
		dbPassword = getServletContext().getInitParameter("dbPassword");
		
		connection = new InputBean().getConnection(jdbcUrl, dbUsername, dbPassword);
		getServletContext().setAttribute("dbconn", connection);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Process the input
		
		String exceptionMessage = "";
		int exceptionTrigger = 0;
		
		try{
			String secretCode = request.getParameter("secretCode");
			String logicCode = request.getParameter("logicCode");
			InputBean inputbean = new InputBean();
			
			
			if(secretCode == logicCode){
				inputbean.DropTable(connection);
			}else{
				inputbean.setSecretCode(secretCode);
				inputbean.setLogicCode(logicCode);
				inputbean.insertRecord(connection);
			}
			request.getSession().setAttribute("inputbean", inputbean);
			
			
		}catch(InvalidInputException iie){
			request.setAttribute("error", exceptionMessage);
		}
		
		if(exceptionTrigger==0){
			response.sendRedirect("display.jsp");
		}else{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
