package com.servlet;
import com.service.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * Servlet implementation class SimpleSearcherServlet
 */
@WebServlet("/SimpleSearcherServlet")
public class SimpleSearcherServlet extends HttpServlet {
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
		response.setContentType("text/html");
		String searchkey =request.getParameter("Compsearch");
		
		PrintWriter out =response.getWriter();
		
		File indexDir = new File("C:\\temp\\facebook\\index");
		String query = searchkey;
		int hits = 100;
		
					// TODO Auto-generated catch block
					
			Directory directory = FSDirectory.open(indexDir);

			IndexSearcher searcher = new IndexSearcher(directory);
			QueryParser parser = new QueryParser(Version.LUCENE_30, "contents", new SimpleAnalyzer());
			Query squery = null;
			try {
				squery = parser.parse(query);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TopDocs topDocs = searcher.search(squery, hits);
			
			ScoreDoc[] mhits = topDocs.scoreDocs;
			for (int i = 0; i < mhits.length; i++) {
				int docId = mhits[i].doc;
				Document d = searcher.doc(docId);
				
				//out.print("<a href=");
				out.println("<a href= " + d.get("filename") +">");
				out.println( d.get("filename"));
				//out.println("</a>");
				out.println("<br>");
				
			}
			
			System.out.println("Found " + mhits.length);
		}
		
	}


