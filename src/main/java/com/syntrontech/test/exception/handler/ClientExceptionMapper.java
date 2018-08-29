package com.syntrontech.test.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.syntrontech.test.exception.client.ClientException;
import com.syntrontech.test.util.ResponseStatus;

public class ClientExceptionMapper implements ExceptionMapper<ClientException>{

	@Override
	public Response toResponse(ClientException exception) {
		return ResponseStatus._400(exception.getErrorCode(), exception.getErrorMessage());
	}

}
