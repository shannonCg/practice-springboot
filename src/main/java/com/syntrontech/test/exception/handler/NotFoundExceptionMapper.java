package com.syntrontech.test.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.syntrontech.test.exception.client.NotFoundException;
import com.syntrontech.test.util.ResponseStatus;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		return ResponseStatus._404();
	}

}
