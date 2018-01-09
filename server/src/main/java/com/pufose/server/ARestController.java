package com.pufose.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/api/path{source}TO{sink}IN{city}")
	public String path(@PathVariable String source, @PathVariable String sink, @PathVariable String city) {
		List<String> minpath= service.getShortestPath(source, sink, Integer.parseInt(city));
		Gson serializer=new Gson();
		return serializer.toJson(minpath);
	}
	
	@GetMapping("/api/grid{name}")
	public String city(@PathVariable String name) {
		Gson serializer=new Gson();
		return serializer.toJson(service.getById(Integer.parseInt(name)));
	}
	
	

}