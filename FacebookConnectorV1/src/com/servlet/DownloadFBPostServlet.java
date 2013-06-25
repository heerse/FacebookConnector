package com.servlet;
import com.service.*;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadFBPostServlet
 */
@WebServlet("/DownloadFBPostServlet")
public class DownloadFBPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, Htt
	 * pServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("facebookuser");
		
		DownloadFBPost fbPost= new DownloadFBPost();
		fbPost.FBPostDownload(username);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/PublicPost.jsp");
		requestDispatcher.forward(request , response);
		
		//Run Indexer
		File indexDir = new File(".\\SocilaMedia\\facebook\\index");
		File dataDir = new File("C:\\temp\\facebook");
		String suffix = "txt";
		
		SimpleFileIndexer indexer = new SimpleFileIndexer();
		
		try {
			int numIndex = indexer.index(indexDir, dataDir, suffix);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
