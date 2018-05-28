package com.carson.exceptions;

public class IllegalBlockException extends Exception{

	private static final long serialVersionUID = 2469010451674101088L;

	@Override
	public void printStackTrace() {
		super.printStackTrace();
		System.err.println("Illegal block rejected from being added to the blockchain");	
	}
	
}
