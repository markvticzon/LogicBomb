package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InputBean;

/**
 * Servlet implementation class ViewRecordsServlet
 */
@WebServlet("/records.html")
public class ViewRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = new InputBean().getConnection(
				getServletContext().getInitParameter("jdbcUrl"),
				getServletContext().getInitParameter("dbUsername"),
				getServletContext().getInitParameter("dbPassword"));
		ResultSet records =
				new InputBean().getAllRecords(connection);
		request.setAttribute("records", records);
		request.getRequestDispatcher("records.jsp").forward(request, response);
	}

}
