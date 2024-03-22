package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDAO;
import db.DB;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String password = request.getParameter("password");
	     String email = request.getParameter("email");
	     
	     LoginUser lu = new LoginUser();
	     lu.setEmail(email);
	     lu.setPassword(password);
	     
	     PrintWriter out = response.getWriter();
	     
	     try {
	            Connection connection = DB.getConnection();
	            UserDAO userDAO = new UserDAO(connection);
	            String result = userDAO.loginChk(lu);
	            
	            if(result != null) {
	            	response.sendRedirect("Index.jsp");
	            }else {
	            	 // Login failed, display warning message in login page
	                out.println("<script>alert('Either username or password does not match');</script>");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
	                dispatcher.include(request, response);
	            }
	            connection.close();
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
