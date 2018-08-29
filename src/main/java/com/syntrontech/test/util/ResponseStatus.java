package com.syntrontech.test.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.syntrontech.test.exception.model.ErrorVO;

public class ResponseStatus {
	
	public static Response _404(){
		return Response
				.status(Response.Status.NOT_FOUND)
				.build();
	}
	
	public static Response _401(){
		return Response
				.status(Response.Status.UNAUTHORIZED)
				.build();
	}
	
	public static Response _403(){
		return Response
				.status(Response.Status.FORBIDDEN)
				.build();
	}
	
	public static Response _400(String code, String message){
		ErrorVO error = new ErrorVO(code, message);

		return Response
				.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(error)
				.build();
	}
	
	public static Response _500(String code, String message){
		ErrorVO error = new ErrorVO(code, message);
		
		return Response
				 .status(Response.Status.INTERNAL_SERVER_ERROR)
				 .type(MediaType.APPLICATION_JSON)
				 .entity(error)
				 .build();
	}
}
