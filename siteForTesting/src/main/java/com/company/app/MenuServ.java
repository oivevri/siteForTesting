package com.company.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/MenuServ", "/amind/MenuServ", "/data/MenuServ" })
public class MenuServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuServ() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/data/menu.jsp").forward(request, response);
	}
}
