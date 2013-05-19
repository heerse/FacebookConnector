package com.introviz;

import com.restfb.Facebook;

public class FqlUser {

	  @Facebook
	  String message;
	  
	 

	  @Override
	  public String toString() {
	    return String.format("%s)", message);
	  }
}
