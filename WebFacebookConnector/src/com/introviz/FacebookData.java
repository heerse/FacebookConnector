package com.introviz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;

/**
 * Servlet implementation class FacebookData
 */
@WebServlet("/FacebookData")
public class FacebookData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*PrintWriter out = response.getWriter();
		String username=request.getParameter("facebookuser");
		FacebookClient publicOnlyFacebookClient  = new DefaultFacebookClient();
		Connection<Post> publicSearch = publicOnlyFacebookClient .fetchConnection("Search",Post.class,Parameter.with("q",username),Parameter.with("type","post"));
		out.println("Public search: " + publicSearch.getData().get(0).getMessage());

FileOutputStream fos = new FileOutputStream("F:\\Java\\Facebook files\\Sample.txt", true);

fos.write((publicSearch.getData().get(0).getMessage().getBytes()));*/

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/PublicPost.jsp");
		requestDispatcher.forward(request , response);
	}

}
