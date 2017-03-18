package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.LoginDao;
import com.mvc.dto.Logindto;

@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginPage() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		HttpSession session=null;
		String user=null;
		String password=null;
		String uservalidate=null;
		Logindto usercheck=new Logindto();
		LoginDao logindao=new LoginDao();
		user=request.getParameter("user");
		password=request.getParameter("password");
		usercheck.setUser(user);
		usercheck.setPassword(password);
		try {
			uservalidate=logindao.userauthenticate(usercheck);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		//it gives that user and password are correct or wrong
		//if password are correct then it gives successes
		//else it gives not correct 
		//if it will give correct then we will take it is 
		//correct user other wise wrong user
		if(uservalidate.equals("SUCCESS")){
			session=request.getSession();
		session.setAttribute("username",user);
		request.getRequestDispatcher("/Home.jsp").forward(request, response);
				}
		else{
			request.setAttribute("errormsg", uservalidate);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
					}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
