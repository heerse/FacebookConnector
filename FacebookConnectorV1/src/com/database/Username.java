package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class Username {
	
	
	
public List<String> userName(){
	
	Connection connection = null;
	Statement st= null;
	ResultSet rs=null;
	List<String> username = new ArrayList<String>();
	
	System.out.println("-------- PostgreSQL "
			+ "JDBC Connection Testing ------------");

	
try{
		Class.forName("org.postgresql.Driver");


		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres","admin");
		
		st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM \"sm_users\"");

     
      
        
       while (rs.next()) {
        	System.out.println(rs.getString("facebook"));
        	username.add(rs.getString("facebook"));
			
			
		
        
        
        /* Iterator<String> iterator = username.iterator();
        while (iterator.hasNext()) {
        	System.out.println(iterator.next());	
	
		}*/
//return username;
       }
	 
}catch (SQLException e) {

	System.out.println("Connection Failed! Check output console");
	e.printStackTrace();
	//return;

}catch (ClassNotFoundException e) {

	System.out.println("Where is your PostgreSQL JDBC Driver? "
			+ "Include in your library path!");
	e.printStackTrace();
	//return;

}finally{
     //finally block used to close resources
                 
					try {
						if(st!=null)
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
     }
     try{
        if(connection!=null)
        	connection.close();
     }catch(SQLException se){
        se.printStackTrace();
     }//end finally try
	return username;
	
}}
