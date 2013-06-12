package com.introviz;


import java.sql.Timestamp;

import com.restfb.Facebook;

public class FqlUser {

	  @Facebook
	  String message;
	  
	  @Facebook
	  String created_time;
	   

	  @Override
	  public String toString() {
	    return String.format("%s (%s)", message,created_time);
	  }
}
