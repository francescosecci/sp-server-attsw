package com.pufose.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AWebController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}
