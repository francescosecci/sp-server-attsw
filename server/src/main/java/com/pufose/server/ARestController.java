package com.pufose.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.Gson;

@RestController
public class ARestController {
	@Autowired
	private IGraphService service;
	
	@GetMapping("/")
	public String isUp() {
		return "";
	}
	
	@GetMapping("/api")
	public String index() {
		Gson serializer=new Gson();
		List<String> names=service.getAllNames();
		return serializer.toJson(names);
		
	}
	
	

}