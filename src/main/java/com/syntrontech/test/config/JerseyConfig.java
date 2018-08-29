package com.syntrontech.test.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.syntrontech.test.exception.handler.ClientExceptionMapper;
import com.syntrontech.test.exception.handler.NotFoundExceptionMapper;
import com.syntrontech.test.restful.resource.StudentResource;

/*
 * register the Endpoints in Jersey
 */

@Component
@ApplicationPath("/test")
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig(){
		registerResource();
		registerProvider();
	}
	
	private void registerResource(){
		register(StudentResource.class);
	}
	
	private void registerProvider(){
		register(new ClientExceptionMapper());
		register(new NotFoundExceptionMapper());
	}
}
