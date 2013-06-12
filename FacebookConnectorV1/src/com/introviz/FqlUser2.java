package com.introviz;

import com.restfb.Facebook;

public class FqlUser2 {

	  @Facebook
	  String page_stories;
	  
	

	  @Override
	  public String toString() {
	    return String.format("%s ", page_stories);
	  }

}
