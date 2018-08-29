package com.syntrontech.test.exception.client;

public abstract class ClientException extends Exception{

	private static final long serialVersionUID = 5892136923320185397L;

	public ClientException(){
		super();
	}
	
	public ClientException(String message, Throwable cause){
		super(message, cause);
	}
	
	public abstract String getErrorCode();
	
	public abstract String getErrorMessage();

}
