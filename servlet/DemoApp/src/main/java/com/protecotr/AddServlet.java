package com.protecotr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6906244373960399697L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		
		int k = i + j;
		
		PrintWriter out = res.getWriter();
		
		out.println("<html><body bgcolor='cyan'></body></html>");
		
		out.println("Output : " + k);
		out.println("</body></html>");
//		
//		Cookie cookie = new Cookie("k", k + "");
//		
//		res.addCookie(cookie);
		
//		HttpSession session = req.getSession();
//		
//		session.setAttribute("k", k);
		
		res.sendRedirect("sq");
		
//		req.setAttribute("k", k);
		

//		RequestDispatcher rd = req.getRequestDispatcher("sq");
//		rd.forward(req, res);
	}

}
