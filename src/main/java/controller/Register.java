package controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;
import db.DB;
import dao.UserDAO;


/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Register() {
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
	        String name = request.getParameter("name");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        String address = request.getParameter("address");

	        User user = new User();
	        user.setName(name);
	        user.setPassword(password);
	        user.setAddress(address);
	        user.setEmail(email);
	        user.setPhone(phone);
	        
			PrintWriter out = response.getWriter();

	        try {
	            Connection connection = DB.getConnection();
	            UserDAO userDAO = new UserDAO(connection);
	            userDAO.addUser(user);
	            connection.close();
	            out.println("<h1>User Created Successfully</h1>");
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}

}
