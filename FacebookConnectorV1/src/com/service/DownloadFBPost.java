package com.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.introviz.FqlUser;
import com.introviz.FqlUser1;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.exception.FacebookException;
import com.restfb.exception.FacebookGraphException;
import com.restfb.exception.FacebookJsonMappingException;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.exception.FacebookResponseStatusException;

public class DownloadFBPost {

	public void FBPostDownload(String username) throws FileNotFoundException, IOException{
		
GregorianCalendar gc = new GregorianCalendar();
		
		File f=new File("C:\\temp\\facebook\\"+ gc.get(Calendar.YEAR)+ "\\" + gc.get(Calendar.MONTH) +"\\"+gc.get(Calendar.DATE) );
		
		f.mkdirs();// creating directoriesile
		
		File findex= new File("C:\\temp\\facebook\\index");
		findex.mkdir();
		
		FileWriter fw= new FileWriter("C:\\temp\\facebook\\"+ gc.get(Calendar.YEAR)+ "\\" + gc.get(Calendar.MONTH) +"\\"+gc.get(Calendar.DATE)+ "\\"+username+".txt");
		
		
		
		try {
			
			//This will run only for valid access token

			AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken("133070086893185","0960b503e7a0644e82e299613e6c75cd");

			FacebookClient publicOnlyFacebookClient  = new DefaultFacebookClient(accessToken.getAccessToken());
			//Connection<Page> publicSearch = publicOnlyFacebookClient .fetchConnection("Search",Page.class,Parameter.with("q",username),Parameter.with("type","page"));
			/*while (publicSearch.iterator().hasNext()){
			int i=0;
			out.println("Public search: " + publicSearch.getData().get(i).getAbout() +"... ");
			i++;
			}*/

			String query1="Select id FROM profile WHERE username="+username;
			List<FqlUser1> users1= publicOnlyFacebookClient.executeFqlQuery(query1,FqlUser1.class );

			//out.println("page id: " + users1);

			//String query2 = "SELECT page_stories FROM insights WHERE object_id="+users1.get(0)+"AND period=0";

			String query = "SELECT message,created_time FROM stream WHERE source_id="+users1.get(0)+"AND created_time <now() and created_time >1193840000 " ;
			//String query = "SELECT message,created_time FROM stream WHERE source_id="+users1.get(0)+"AND LIMIT 1000 OFFSET 0 " ;
			//String query1="Select page_id FROM page WHERE username="+username;
			//String query = "SELECT message, post_id FROM stream WHERE source_id IN(Select page_id FROM WHERE username='AllState')";

			List<FqlUser> users=  publicOnlyFacebookClient.executeFqlQuery(query,FqlUser.class );
			
			//FileOutputStream fos = new FileOutputStream("F:\\Java\\Facebook files\\Sample.txt", true);
			//Runtime.getRuntime().exec("mkdir[C:\\Users\\HEER\\Desktop\\runtime\\New]");
			//fos.write(users);
			
			//out.println("Public Posts on the page : " + users);
			File sample1= new File(".\\SocilaMedia\\facebook\\"+ gc.get(Calendar.YEAR)+ "\\" + gc.get(Calendar.MONTH) +"\\"+gc.get(Calendar.DATE)+ "\\"+username+".txt");
			FileOutputStream is = new FileOutputStream(sample1);
	        OutputStreamWriter osw = new OutputStreamWriter(is);    
	        Writer w = new BufferedWriter(osw);
	       
	        w.write(users.toString());
		
	        w.close();

			}catch (FacebookJsonMappingException e) {
				System.out.println("Jason Mapping error");
				  // Looks like this API method didn't really return a list of users
				} catch (FacebookNetworkException e) {
				  // An error occurred at the network level
					System.out.println("API returned HTTP status code " + e.getHttpStatusCode());
				} catch (FacebookOAuthException e) {
				  // Authentication failed - bad access token?  
					System.out.println("Authorization failed!");
				} catch (FacebookGraphException e) {
				  // The Graph API returned a specific error
					System.out.println("Call failed. API says: " + e.getErrorMessage());
				} catch (FacebookResponseStatusException e) {
				  // Old-style Facebook error response.
				  // The Graph API only throws these when FQL calls fail.
				  // You'll see this exception more if you use the Old REST API
				  // via LegacyFacebookClient.
					System.out.println("Resonse status exception!");
				  if (e.getErrorCode() == 200)
					  System.out.println("Permission denied!");
				} catch (FacebookException e) {
					System.out.println("Facebook Exception");
				  // This is the catchall handler for any kind of Facebook exception
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Invalid Keyword");
				  // This is the catchall handler for any kind of Facebook exception
				}

	}
}
