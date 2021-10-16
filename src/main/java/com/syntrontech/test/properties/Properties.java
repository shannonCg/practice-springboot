package com.syntrontech.test.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="my")
public class Properties {

	private List<String> servers = new ArrayList<>();
	
	public List<String> getServers(){
		return servers;
	}
}
