package com.carson.exceptions;

public class IllegalChainException extends Exception{


	
	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		super.printStackTrace();
		System.err.println("Chain is not valid");	
	}
	
}
