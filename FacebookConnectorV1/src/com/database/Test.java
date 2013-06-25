package com.database;

import java.util.Iterator;

import com.restfb.types.User;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Username users= new Username();
		
		users.userName();
		Iterator<String> iterator = users.userName().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}

	}

}
