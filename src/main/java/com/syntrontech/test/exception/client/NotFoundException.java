package com.syntrontech.test.exception.client;

public class NotFoundException extends Exception{

	private static final long serialVersionUID = -5188195792569968018L;
	
	public NotFoundException(){
		super();
	}
	
	public NotFoundException(String message, Throwable cause){
		super(message, cause);
	}

}
