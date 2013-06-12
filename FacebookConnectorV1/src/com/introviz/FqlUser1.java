package com.introviz;

import com.restfb.Facebook;

public class FqlUser1 {
	  @Facebook
	  String id;
	  
	

	  @Override
	  public String toString() {
	    return String.format("%s ", id);
	  }

}
